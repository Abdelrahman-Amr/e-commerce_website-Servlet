package gov.iti.jets.persistence.dao;

import gov.iti.jets.entity.Category;
import gov.iti.jets.entity.Customer;
import gov.iti.jets.entity.OrderMaster;
import gov.iti.jets.util.MyLocal;
import jakarta.persistence.Query;

import java.util.List;

public class OrderMasterDAO extends BaseDAO<OrderMaster>{
    private volatile static OrderMasterDAO orderMasterDAO;

    private OrderMasterDAO()
    {
        super(OrderMaster.class, MyLocal.getInstance().get());
    }

    public static OrderMasterDAO getInstance() {
        if (orderMasterDAO == null) {
            synchronized (OrderMasterDAO.class) {
                if (orderMasterDAO == null) {
                    orderMasterDAO = new OrderMasterDAO();
                }
            }
        }
        return orderMasterDAO;
    }



    public List<OrderMaster> getAll()
    {
        Query query = entityManager.createQuery("from OrderMaster");
        List<OrderMaster> orders = query.getResultList();
        return  orders;
    }



}

