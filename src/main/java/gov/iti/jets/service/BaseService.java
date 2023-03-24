package gov.iti.jets.service;

import gov.iti.jets.entity.BaseEntity;
import gov.iti.jets.persistence.dao.BaseDAO;
import jakarta.persistence.EntityManager;
import org.mapstruct.Mapper;

public abstract  class BaseService <E extends BaseEntity>{

    protected BaseDAO<E> dao;

    public E get(Long id)
    {
        return dao.get(id);
    }

    public void update(E entity)
    {
        dao.update(entity);
    }
    public void save(E entity)
    {
        dao.save(entity);
    }
    public void setManager(EntityManager manager)
    {
        this.dao.setManager(manager);
    }



}
