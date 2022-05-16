package manager;

import persistance.Friend;
import persistance.Message;
import repository.FriendRepository;
import repository.MessageRepository;
import repository.PersonsRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class MessageCommand extends Command{
    @Override
    boolean validate(List<String> commandArgs) {
        return true;
    }

    public void execute(String bulkArg, EntityManager em, String lastLoggedInUser) {
        PersonsRepository personsRepository = new PersonsRepository(em);
        FriendRepository friendRepository = new FriendRepository(em);
        MessageRepository messageRepository = new MessageRepository(em);

        Long lastId = personsRepository.findByName(lastLoggedInUser).getId();
        List<Friend> friendList = friendRepository.findById(lastId);
        for (var friend: friendList) {
            Long id1 = friend.getId().getId1();
            Long id2 = friend.getId().getId2();
            Long newId = id1.equals(lastId) ? id2 : id1;
            Message message = new Message(newId, bulkArg);
            messageRepository.persist(message);
        }
    }
}
