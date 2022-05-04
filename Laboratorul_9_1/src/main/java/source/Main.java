package source;

import entity.CitiesEntity;
import entity.ContinentsEntity;
import entity.CountriesEntity;
import manager.CityRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigInteger;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ExamplePU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        ContinentsEntity continent = new ContinentsEntity(BigInteger.valueOf(100), "Europe");
        System.out.println(continent);
        em.persist(continent);

        ContinentsEntity c = (ContinentsEntity) em.createQuery(
                        "select e from ContinentsEntity e where e.name='Europe'")
                .getSingleResult();
        c.setName("Africa");
        em.getTransaction().commit();

//        CityRepository cityRepository = new CityRepository(emf);
//        CountriesEntity countriesEntity = new CountriesEntity(BigInteger.valueOf(1), "country0", "code0", "continent0");
//        List<CitiesEntity> citiesEntityList = cityRepository.findByCountry(countriesEntity);
//        for (CitiesEntity citiesEntity: citiesEntityList)
//            System.out.println(citiesEntity);
        em.close();
        emf.close();
    }
}
