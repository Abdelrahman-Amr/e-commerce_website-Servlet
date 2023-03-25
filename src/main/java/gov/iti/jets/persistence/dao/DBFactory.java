package gov.iti.jets.persistence.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBFactory {
    private final static EntityManagerFactory entityManagerFactory =Persistence.createEntityManagerFactory("coffee");;

    private DBFactory() {

    }

    public static EntityManagerFactory getInstance() {
        return entityManagerFactory;
    }
}
