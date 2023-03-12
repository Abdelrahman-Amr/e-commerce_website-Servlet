package gov.iti.jets.persistence.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBManager {
    private volatile static EntityManagerFactory entityManagerFactory;

    private DBManager() {

    }

    public static EntityManagerFactory getInstance() {
        if (entityManagerFactory == null) {
            synchronized (EntityManagerFactory.class) {
                if (entityManagerFactory == null) {
                     entityManagerFactory = Persistence.createEntityManagerFactory("coffee");
                }
            }
        }
        return entityManagerFactory;
    }
}
