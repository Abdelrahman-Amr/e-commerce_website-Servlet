package gov.iti.jets.listener;

import gov.iti.jets.persistence.dao.DBFactory;
import gov.iti.jets.service.CustomerService;
import gov.iti.jets.util.MyLocal;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;

public class ThreadLocalListener implements ServletRequestListener {



    @Override
    public void requestInitialized(ServletRequestEvent sre){
         MyLocal.getInstance().set(DBFactory.getInstance().createEntityManager());
         CustomerService.getInstance().setManager(MyLocal.getInstance().get());
    }
    @Override
    public void requestDestroyed(ServletRequestEvent sre){
        MyLocal.getInstance().remove();
    }

}