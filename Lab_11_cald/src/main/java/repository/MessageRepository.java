package repository;

import persistance.Message;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.List;

public class MessageRepository extends DataRepository<Message, BigInteger> {
    public MessageRepository(EntityManager em) {
        this.em = em;
    }

    public List<Message> findById(Long id) {
        return em.createNamedQuery("Message.findById")
                .setParameter("id", id)
                .getResultList();
    }
}
