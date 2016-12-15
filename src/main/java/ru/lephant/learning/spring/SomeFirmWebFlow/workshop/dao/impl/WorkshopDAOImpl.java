package ru.lephant.learning.spring.SomeFirmWebFlow.workshop;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Workshop;
import java.util.List;

@Repository
public class WorkshopDAOImpl implements WorkshopDAO {

    @Autowired
    SessionFactory sessionFactory;

    public List listWorkshop() {
        return null;
    }

    public Workshop getWorkshopById(long id) {
        return null;
    }

    public void deleteWorkshop(long id) {

    }

    public void saveWorkshop(Workshop workshop) {

    }

}
