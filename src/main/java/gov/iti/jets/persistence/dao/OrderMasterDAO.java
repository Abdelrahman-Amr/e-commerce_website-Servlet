package gov.iti.jets.persistence.dao;

import gov.iti.jets.entity.Category;
import gov.iti.jets.entity.Customer;
import gov.iti.jets.entity.OrderMaster;
import gov.iti.jets.util.MyLocal;
import jakarta.persistence.Query;

import java.util.List;

public class OrderMasterDAO extends BaseDAO<OrderMaster>{

    public OrderMasterDAO()
    {
        super(OrderMaster.class, DBFactory.getInstance().createEntityManager());
    }


    public List<OrderMaster> getAll()
    {
        Query query = entityManager.createQuery("from OrderMaster");
        List<OrderMaster> orders = query.getResultList();
        return  orders;
    }



}

