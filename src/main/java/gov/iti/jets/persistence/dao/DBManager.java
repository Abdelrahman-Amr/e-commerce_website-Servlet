package gov.iti.jets.persistence.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBManager {
    private volatile static EntityManager entityManager;

    private DBManager() {

    }

    public static EntityManager getInstance() {
        if (entityManager == null) {
            synchronized (EntityManager.class) {
                if (entityManager == null) {
                    System.out.println("yes");
                    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("coffee");
                    entityManager = entityManagerFactory.createEntityManager();
                }
            }
        }
        return entityManager;
    }
}
