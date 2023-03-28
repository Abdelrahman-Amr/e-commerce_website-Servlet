package gov.iti.jets.listener;

import gov.iti.jets.persistence.dao.DBFactory;
import gov.iti.jets.service.CategoryService;
import gov.iti.jets.service.CustomerService;
import gov.iti.jets.service.ProductService;
import gov.iti.jets.service.SizeService;
import gov.iti.jets.util.MyLocal;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;

public class ThreadLocalListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre){
         MyLocal.getInstance().set(DBFactory.getInstance().createEntityManager());
//         MyLocal.getInstance().get().getTransaction().begin();

//         CustomerService.getInstance().setManager(MyLocal.getInstance().get());
//         CategoryService.getInstance().setManager(MyLocal.getInstance().get());
//         ProductService.getInstance().setManager(MyLocal.getInstance().get());
//         SizeService.getInstance().setManager(MyLocal.getInstance().get());
    }
    @Override
    public void requestDestroyed(ServletRequestEvent sre){

//        MyLocal.getInstance().remove();
    }

}
