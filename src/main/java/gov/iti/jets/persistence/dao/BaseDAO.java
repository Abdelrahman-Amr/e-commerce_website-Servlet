package gov.iti.jets.persistence.dao;

import gov.iti.jets.entity.BaseEntity;
import gov.iti.jets.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;


public abstract class BaseDAO <E extends BaseEntity>{

    protected  EntityManager entityManager;

    CriteriaBuilder criteriaBuilder;
    private Class<E> entity;

    public BaseDAO (Class<E> entity, EntityManager entityManager )
    {
        this.entity  = entity;
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();

    }

    public E get(Long id)
    {
      return entityManager.find(entity,id);
    }

    public boolean update(E entity)
    {
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
        } catch (Exception e) {
            return false;
        }
        finally{
            entityManager.getTransaction().commit();
        }
        return true;
    }
    public boolean save(E entity)
    {
        boolean result = true;
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
        } catch (Exception e) {
            result =  false;
        }
        finally{
            entityManager.getTransaction().commit();
        }
        return result;
    }

    public void merge(E entity)
    {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
//        entityManager.refresh(entity);
    }
    public void refreshCustomer(E entity)
    {
        entityManager.getTransaction().begin();
        entityManager.refresh(entity);
        entityManager.getTransaction().commit();
    }

    public void setManager(EntityManager manager)
    {
        entityManager = manager;
    }
}
