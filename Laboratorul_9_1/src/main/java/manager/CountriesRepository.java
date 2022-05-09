package manager;

import entity.CountriesEntity;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.List;

public class CountriesRepository extends DataRepository<CountriesEntity, BigInteger>{
    public CountriesRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public CountriesEntity findById(BigInteger id) {
        return (CountriesEntity) em.createNamedQuery("CountriesEntities.findById")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<CountriesEntity> findByName(String name) {
        return em.createNamedQuery("CountriesEntities.findByName")
                .setParameter("name", name)
                .getResultList();
    }
}
