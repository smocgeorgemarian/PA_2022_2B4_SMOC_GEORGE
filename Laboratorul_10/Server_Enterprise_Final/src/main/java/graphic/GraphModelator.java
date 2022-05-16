package graphic;

import manager.FactoryManager;
import persistance.Friend;
import repository.FriendRepository;
import repository.PersonsRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class GraphModelator {
    private GraphModelator() {}
    private static List<List<Integer>> getModeledList(int listSize, List<Friend> friendList) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            List<Integer> tmpList = new ArrayList<>();
            adjacencyList.add(tmpList);
        }
        for (var friend: friendList) {
            Long f1 = friend.getId().getId1();
            Long f2 = friend.getId().getId2();
            int int1 = (int) f1.longValue();
            int int2 = (int) f2.longValue();
            int1--;
            int2--;

            adjacencyList.get(int1).add(int2);
            adjacencyList.get(int2).add(int1);
        }
        System.out.println(adjacencyList);
        return adjacencyList;
    }

    public static List<List<Integer>> getAdjacencyList() {
        EntityManagerFactory emf = FactoryManager.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        FriendRepository friendRepository = new FriendRepository(em);
        PersonsRepository personsRepository = new PersonsRepository(em);
        List<Friend> friendList = friendRepository.findAll();
        int listSize = (int) personsRepository.Count().longValue();
        return getModeledList(listSize, friendList);
    }

}
