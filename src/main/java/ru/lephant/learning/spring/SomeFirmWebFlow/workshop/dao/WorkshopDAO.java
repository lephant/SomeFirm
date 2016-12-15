package ru.lephant.learning.spring.SomeFirmWebFlow.workshop;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Workshop;
import java.util.List;

public interface WorkshopDAO {

    public List listWorkshop();

    public Workshop getWorkshopById(long id);

    public void deleteWorkshop(long id);

    public void saveWorkshop(Workshop workshop);

}
