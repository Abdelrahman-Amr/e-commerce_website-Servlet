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

    public OrderMaster searchForCart(Long customerId)
    {
        Query query = entityManager.createQuery("from OrderMaster o where  o.cust.id =:customerId and o.isCart =true order by o.id desc");
        query.setParameter("customerId",customerId);
        List<OrderMaster> orders = query.getResultList();
        System.out.println(orders.size());
        if(orders!= null && orders.size()>0)
        {
            System.out.println("size = "+orders.get(0).getOrderDetails().size());
            return orders.get(0);
        }
        return  null;
    }

    public void deleteCart(Long customerId)
    {
        OrderMaster orderMaster = searchForCart(customerId);
//        System.out.println(orderMaster.getId());
        if(orderMaster!=null)
        {
            entityManager.getTransaction().begin();
            entityManager.remove(orderMaster);
            entityManager.getTransaction().commit();
        }
//        Query query = entityManager.createQuery("delete from OrderMaster o where  o.cust.id =:customerId and o.isCart =true ");
//        query.setParameter("customerId",customerId);
//        query.executeUpdate();
    }

}

