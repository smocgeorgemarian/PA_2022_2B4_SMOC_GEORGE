package manager;

import entity.CitiesEntity;
import entity.CountriesEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class CityRepository {

    private final EntityManager em; //create it somehow

    public CityRepository(EntityManagerFactory emf) {
        em = emf.createEntityManager();
    }

    public List<CitiesEntity> findByCountry(CountriesEntity country) {
        return em.createNamedQuery("CitiesEntities.findByCountry")
                .setParameter("country", country)
                .getResultList();
    }
}