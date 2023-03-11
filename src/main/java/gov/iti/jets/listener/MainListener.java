package gov.iti.jets.listener;

import gov.iti.jets.persistence.dao.DBManager;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class MainListener implements ServletContextListener {

    public void contextInitialized (ServletContextEvent cse) {
        DBManager.getInstance();
    }
    public void contextDestroyed (ServletContextEvent cse) {
        System.out.println("Application shut down");
    }
}
