package result_modeler;

import persistance.Friend;
import persistance.Person;
import repository.FriendRepository;
import repository.PersonsRepository;
import result_modeler.CustomComparator;
import result_modeler.CustomEntry;

import java.util.*;

public class TopCreator {

    private final PersonsRepository personsRepository;
    private final FriendRepository friendRepository;
    private final Map<Integer, Integer> freqMap;

    public TopCreator(PersonsRepository personsRepository, FriendRepository friendRepository) {
        this.personsRepository = personsRepository;
        this.friendRepository = friendRepository;
        freqMap = new HashMap<>();
    }

    private void insertIfNeeded(int id) {
        if (freqMap.containsKey(id)) {
            int newValue = freqMap.get(id) + 1;
            freqMap.put(id, newValue);
            return;
        }
        freqMap.put(id, 0);
    }

    public List<Person> execute(int k) {
        List<Friend> friendList = friendRepository.findAll();
        for (var friend: friendList) {
            int id1 = friend.getId().getId1().intValue();
            int id2 = friend.getId().getId2().intValue();
            insertIfNeeded(id1);
            insertIfNeeded(id2);
        }
        PriorityQueue<CustomEntry> priorityQueue = new PriorityQueue<>(new CustomComparator());
        for (var entry: freqMap.entrySet()) {
            CustomEntry customEntry = new CustomEntry(entry.getKey(), entry.getValue());
            priorityQueue.offer(customEntry);
        }
        List<Person> personList = new ArrayList<>();
        for (int pollIndex = 0; pollIndex < k; pollIndex++) {
            long id = priorityQueue.poll().getId();
            personList.add(personsRepository.findById(id));
        }
        return personList;
    }
}
