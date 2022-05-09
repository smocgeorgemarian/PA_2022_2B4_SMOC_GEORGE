package manager;

import entity.ContinentsEntity;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.List;

public class ContinentsRepository extends DataRepository<ContinentsEntity, BigInteger>{
    public ContinentsRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public ContinentsEntity findById(BigInteger id) {
        return (ContinentsEntity) em.createNamedQuery("ContinentsEntity.findById")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<ContinentsEntity> findByName(String name) {
        return em.createNamedQuery("ContinentsEntity.findByName")
                .setParameter("name", name)
                .getResultList();
    }

}
