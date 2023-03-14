package gov.iti.jets.persistence.dao;

import gov.iti.jets.entity.BaseEntity;
import gov.iti.jets.entity.Customer;
import jakarta.persistence.EntityManager;


public abstract class BaseDAO <E extends BaseEntity>{

    protected  EntityManager entityManager;
    private Class<E> entity;

    public BaseDAO (Class<E> entity, EntityManager entityManager )
    {
        this.entity  = entity;
        this.entityManager = entityManager;
    }

    public E get(long id)
    {
      return entityManager.find(entity,id);
    }

    public void update(E entity)
    {
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
        }
        finally{
            entityManager.getTransaction().commit();
        }
    }
    public void save(E entity)
    {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
        }
        finally{
            entityManager.getTransaction().commit();
        }
    }
}
