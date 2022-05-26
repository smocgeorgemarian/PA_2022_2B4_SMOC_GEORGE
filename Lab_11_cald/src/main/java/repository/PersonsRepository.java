package repository;

import manager.FactoryManager;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import persistance.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class PersonsRepository implements JpaRepository<Person, Integer> {
    private final EntityManager em;
    public PersonsRepository() {
        EntityManagerFactory emf = FactoryManager.getEntityManagerFactory();
        this.em = emf.createEntityManager();
    }

    @Override
    public List<Person> findAll() {
        return em.createNamedQuery("Person.findAll")
                .getResultList();
    }

    @Override
    public List<Person> findAll(Sort sort) {
        return em.createNamedQuery("Person.findAll")
                .getResultList();
    }

    @Override
    public Page<Person> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Person> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Person entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Person> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Person> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Person> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Person> findById(Integer id) {
        return (Optional<Person>) em.createNamedQuery("Person.findById")
                .setParameter("id", id.longValue())
                .getSingleResult();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Person> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Person> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Person> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Person getOne(Integer integer) {
        return null;
    }

    @Override
    public Person getById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Person> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Person> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Person> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Person> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Person> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Person> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Person, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
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


}
