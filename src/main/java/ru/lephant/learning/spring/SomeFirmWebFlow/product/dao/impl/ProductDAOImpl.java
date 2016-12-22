package ru.lephant.learning.spring.SomeFirmWebFlow.product.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Pressmarks;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.ProductType;
import ru.lephant.learning.spring.SomeFirmWebFlow.enums.ItemType;
import ru.lephant.learning.spring.SomeFirmWebFlow.product.dao.ProductDAO;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    SessionFactory sessionFactory;

    public ProductType getProductByPressmark(long pressmark) {
        Session session = sessionFactory.openSession();
        ProductType productType = (ProductType) session
                .createCriteria(ProductType.class)
                .add(Restrictions.idEq(pressmark))
                .uniqueResult();
        session.close();
        return productType;
    }

    public List listProduct() {
        Session session = sessionFactory.openSession();
        List list = session.createCriteria(ProductType.class).list();
        session.close();
        return list;
    }

    public void saveProduct(ProductType product) {
        Session session = sessionFactory.openSession();
        if (product.getPressmarks() == null) {
            Pressmarks pressmarks = new Pressmarks(product.getPressmark(), ItemType.TOOL);
            product.setPressmarks(pressmarks);
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(pressmarks);
            transaction.commit();
        }
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(product);
        transaction.commit();
        session.close();
    }

    public void deleteProduct(long pressmark) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ProductType product = (ProductType) session.load(ProductType.class, pressmark);
        if (product != null) {
            session.delete(product);
            session.delete(product.getPressmarks());
        }
        transaction.commit();
        session.close();
    }

}
