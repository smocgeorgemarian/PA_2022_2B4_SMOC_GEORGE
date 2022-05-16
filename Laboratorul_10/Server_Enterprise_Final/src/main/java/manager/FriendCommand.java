package manager;

import exception.InvalidNumberOfArgs;
import persistance.Friend;
import persistance.FriendId;
import repository.FriendRepository;
import repository.PersonsRepository;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.List;

public class FriendCommand extends Command {
    @Override
    boolean validate(List<String> commandArgs) {
        return !commandArgs.isEmpty();
    }

    public void insertIntoFriendList(Long personId, Long friendId, List<Friend> friendList, EntityManager em) {
        boolean foundEqual = false;
        for (Friend friend: friendList) {
            Long id1 = friend.getId().getId1();
            Long id2 = friend.getId().getId2();
            if (personId.equals(id1) && friendId.equals(id2) || personId.equals(id2) && friendId.equals(id1)) {
                foundEqual = true;
                break;
            }
        }
        if (foundEqual)
            return;
        FriendId friendId1 = new FriendId(personId, friendId);
        Friend friend = new Friend(friendId1);
        friendList.add(friend);
        FriendRepository friendRepository = new FriendRepository(em);
        friendRepository.persist(friend);
    }

    public void execute(List<String> commandArgs, EntityManager em, String lastUserLoggedIn) throws Exception {
        if (!validate(commandArgs))
            throw new InvalidNumberOfArgs();
        FriendRepository friendRepository = new FriendRepository(em);
        PersonsRepository personsRepository = new PersonsRepository(em);
        Long personId = personsRepository.findByName(lastUserLoggedIn).getId();
        List<Friend> friendList = friendRepository.findById(personId);

        for (var arg: commandArgs) {
            try {
                Long friendId = personsRepository.findByName(arg).getId();
                insertIntoFriendList(personId, friendId, friendList, em);
            }
            catch (Exception e) {
                continue;
            }
        }
    }
}
