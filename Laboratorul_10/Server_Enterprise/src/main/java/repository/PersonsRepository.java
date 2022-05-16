package repository;

import persistance.Person;

import javax.persistence.EntityManager;
import java.math.BigInteger;

public class PersonsRepository extends DataRepository<Person, BigInteger> {
    public PersonsRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Person findById(BigInteger id) {
        return (Person) em.createNamedQuery("Person.findById")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Person findByName(String name) {
        return (Person) em.createNamedQuery("Person.findByName")
                .setParameter("name", name)
                .getSingleResult();
    }
}
