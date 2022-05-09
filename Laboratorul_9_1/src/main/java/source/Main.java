package source;

import entity.ContinentsEntity;
import manager.ContinentsRepository;
import manager.FactoryManager;
import test.TestEntities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigInteger;
import java.util.stream.IntStream;

public class Main {
    public static void testCityRepository(EntityManager em) {
        em.getTransaction().begin();
        ContinentsRepository continentsRepository = new ContinentsRepository(em);
        System.out.println(continentsRepository.findById(BigInteger.valueOf(104)));
        em.getTransaction().commit();
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = FactoryManager.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        TestEntities.testContinentsEntity(em);
        TestEntities.testCountriesEntity(em);
        TestEntities.testCitiesEntity(em);

//        testCityRepository(em);

        em.close();
        emf.close();
    }
}
