package repository;

import persistance.Person;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.List;

public class PersonsRepository extends DataRepository<Person, BigInteger> {
    public PersonsRepository(EntityManager em) {
        this.em = em;
    }

    public Person findById(BigInteger id) {
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
