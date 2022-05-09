package manager;

import entity.CitiesEntity;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.List;

public class CitiesRepository extends DataRepository<CitiesEntity, BigInteger> {
    public CitiesRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public CitiesEntity findById(BigInteger id) {
        return (CitiesEntity) em.createNamedQuery("CitiesEntities.findById")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<CitiesEntity> findByName(String name) {
        return em.createNamedQuery("CitiesEntities.findByName")
                .setParameter("name", name)
                .getResultList();
    }
}