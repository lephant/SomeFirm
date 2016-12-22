package ru.lephant.learning.spring.SomeFirmWebFlow.tool.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Pressmarks;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.ToolType;
import ru.lephant.learning.spring.SomeFirmWebFlow.enums.ItemType;
import ru.lephant.learning.spring.SomeFirmWebFlow.tool.dao.ToolDAO;

import java.util.List;

@Repository
public class ToolDAOImpl implements ToolDAO{

    @Autowired
    SessionFactory sessionFactory;

    public ToolType getToolByPressmark(long pressmark) {
        Session session = sessionFactory.openSession();
        ToolType tool = (ToolType) session.createCriteria(ToolType.class)
                .add(Restrictions.idEq(pressmark))
                .uniqueResult();
        session.close();
        return tool;
    }

    public List listTool() {
        Session session = sessionFactory.openSession();
        List list = session.createCriteria(ToolType.class).list();
        session.close();
        return list;
    }

    public void saveTool(ToolType tool) {
        Session session = sessionFactory.openSession();
        if (tool.getPressmarks() == null) {
            Pressmarks pressmarks = new Pressmarks(tool.getPressmark(), ItemType.TOOL);
            tool.setPressmarks(pressmarks);
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(pressmarks);
            transaction.commit();
        }
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(tool);
        transaction.commit();
        session.close();
    }

    public void deleteTool(long pressmark) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ToolType tool = (ToolType) session.load(ToolType.class, pressmark);
        if (tool != null) {
            session.delete(tool);
            session.delete(tool.getPressmarks());
        }
        transaction.commit();
        session.close();
    }

}
