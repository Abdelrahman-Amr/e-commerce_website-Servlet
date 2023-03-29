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
        super(OrderMaster.class, MyLocal.getInstance().get());
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
        if(orders!= null && orders.size()>0)
        {
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
    public int getRecordsCount() {
        return getAll().size();
    }

    public List<OrderMaster> getOrders(int index) {
        Query query=entityManager.createQuery(" from OrderMaster o",OrderMaster.class).setFirstResult(index).setMaxResults(10);
        List<OrderMaster> masterOrders=query.getResultList();
        return masterOrders;
    }
    public void removeCart(long customerId)
    {
        try {
            entityManager.getTransaction().begin();
            OrderMaster orderMaster = searchForCart(customerId);
//            System.out.println(orderMaster);
            entityManager.remove(orderMaster);
        }catch(Exception ex){
            ex.printStackTrace();
        }

        finally {
            entityManager.getTransaction().commit();
        }
    }
}
