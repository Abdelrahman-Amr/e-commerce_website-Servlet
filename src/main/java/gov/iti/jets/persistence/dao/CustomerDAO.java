package gov.iti.jets.persistence.dao;

import gov.iti.jets.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.rmi.RemoteException;
import java.util.List;

public class CustomerDAO extends BaseDAO<Customer>{

//    private final EntityManager entityManager;
    public CustomerDAO()
    {
        super(Customer.class, DBManager.getInstance().createEntityManager());
//        entityManager =
    }
    public Customer login(String email , String password)
    {
        Query query=entityManager.createQuery("from Customer c where c.email=:u and c.password =:p");
        query.setParameter("u",email);
        query.setParameter("p",password);
        List<Customer> customers=query.getResultList();
        if(customers.size()==0)
        {
            return null;
        }

        return customers.get(0);

    }



}
