package repository;

import manager.FactoryManager;
import org.springframework.stereotype.Repository;
import persistance.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigInteger;
import java.util.List;

@Repository
public class PersonsRepository extends DataRepository<Person, BigInteger> {
    public PersonsRepository() {
        EntityManagerFactory emf = FactoryManager.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        this.em = em;
    }

    public Person findById(Long id) {
        return (Person) em.createNamedQuery("Person.findById")
                .setParameter("id", id)
                .getSingleResult();
    }

    public Person findByName(String name) {
        return (Person) em.createNamedQuery("Person.findByName")
                .setParameter("name", name)
                .getSingleResult();
    }

    public Long Count() {
        return (Long) em.createNamedQuery("Person.findCount")
                .getSingleResult();
    }

    public List<Person> findAll() {
        return em.createNamedQuery("Person.findAll")
                .getResultList();
    }
}
