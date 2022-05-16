package manager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryManager {
    private static EntityManagerFactory emf = null;
    private FactoryManager() {}

    private static void createEntityManagerFactory() {
        emf = Persistence.createEntityManagerFactory("ExamplePU");
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null)
            createEntityManagerFactory();
        return emf;
    }
}
