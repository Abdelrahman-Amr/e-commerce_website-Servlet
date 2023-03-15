package gov.iti.jets.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MyLocal {
    private volatile static ThreadLocal<EntityManager> myLocal;

    private MyLocal() {

    }

    public static ThreadLocal<EntityManager> getInstance() {
        if (myLocal == null) {
            synchronized (MyLocal.class) {
                if (myLocal == null) {
                    myLocal = new ThreadLocal<EntityManager>();
                }
            }
        }
        return myLocal;
    }
}
