package gov.iti.jets.service;

import gov.iti.jets.entity.BaseEntity;
import gov.iti.jets.persistence.dao.BaseDAO;

public abstract  class BaseService <E extends BaseEntity>{

    protected BaseDAO<E> dao;


    public E get(long id)
    {
        return dao.get(id);
    }

    public void save(BaseEntity entity)
    {
        dao.save(entity);
    }
}