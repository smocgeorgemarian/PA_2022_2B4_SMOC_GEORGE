package repository;

import persistance.AbstractEntity;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public abstract class DataRepository<T extends AbstractEntity, ID extends Serializable> {
    protected EntityManager em;

    public abstract T findById(ID id);

    public abstract T findByName(String name);

    public boolean persist(T entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            em.getTransaction().rollback();
        }
        return false;
    }

    public void begin() {em.getTransaction().begin();}

    public void commit() {
        em.getTransaction().commit();
    }
}
