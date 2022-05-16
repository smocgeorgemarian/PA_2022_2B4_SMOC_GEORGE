package repository;

import persistance.Friend;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.List;

public class FriendRepository extends DataRepository<Friend, BigInteger> {
    public FriendRepository(EntityManager em) {
        this.em = em;
    }

    public List<Friend> findById(Long id) {
        return em.createNamedQuery("Friend.findById")
                .setParameter("id", id)
                .getResultList();
    }

    public List<Friend> findAll() {
        return em.createNamedQuery("Friend.findAll")
                .getResultList();
    }
}
