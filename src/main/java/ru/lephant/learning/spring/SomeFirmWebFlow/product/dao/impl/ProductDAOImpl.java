package ru.lephant.learning.spring.SomeFirmWebFlow.product.dao.impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.ProductType;
import ru.lephant.learning.spring.SomeFirmWebFlow.enums.ItemType;
import ru.lephant.learning.spring.SomeFirmWebFlow.product.dao.ProductDAO;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public ProductType getProductByPressmark(long pressmark) {
        Session session = sessionFactory.openSession();
        ProductType productType = (ProductType) session
                .createCriteria(ProductType.class)
                .add(Restrictions.idEq(pressmark))
                .uniqueResult();
        Hibernate.initialize(productType.getOperations());
        session.close();
        return productType;
    }

    @Override
    public List listProduct() {
        Session session = sessionFactory.openSession();
        List list = session.createCriteria(ProductType.class).list();
        session.close();
        return list;
    }

    @Override
    public void createProduct(ProductType product)
            throws ConstraintViolationException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            product.setType(ItemType.PRODUCT);
            session.save(product);
            transaction.commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void updateProduct(ProductType product)
            throws ConstraintViolationException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void deleteProduct(long pressmark)
            throws ConstraintViolationException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            ProductType product = (ProductType) session.load(ProductType.class, pressmark);
            if (product != null) {
                session.delete(product);
            }
            transaction.commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
