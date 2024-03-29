package gov.iti.jets.persistence.dao;

import gov.iti.jets.entity.Product;
import gov.iti.jets.entity.Size;
import gov.iti.jets.util.MyLocal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class SizeDAO extends BaseDAO<Size> {

    public SizeDAO() {
        super(Size.class,MyLocal.getInstance().get());
    }


    public Size getSizeById(Long id) {
        Size size = entityManager.find(Size.class, id);
        return size;
    }

    public List<Size> listAllSizes() {
        TypedQuery query = entityManager.createQuery("select s from Size s", Size.class);
        List<Size> sizes = query.getResultList();
        return sizes;
    }

    public Size  getSizeByName(String name) {
        TypedQuery query = entityManager.createQuery("from Size s where lower(name) like lower(:name)", Size.class);
        query.setParameter("name",name);
        Size size = (Size)query.getSingleResult();
        return size;
    }


}
