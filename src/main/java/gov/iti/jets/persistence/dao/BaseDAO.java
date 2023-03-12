package gov.iti.jets.persistence.dao;

import gov.iti.jets.entity.BaseEntity;
import jakarta.persistence.EntityManager;


public abstract class BaseDAO <E extends BaseEntity>{

    protected static EntityManager entityManager;
    private Class<E> entity;

    public BaseDAO (Class<E> entity)
    {
        this.entity  = entity;
        entityManager = DBManager.getInstance();

    }

    public E get(long id)
    {
      return entityManager.getReference(entity,id);
    }

    public void save(BaseEntity entity)
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
