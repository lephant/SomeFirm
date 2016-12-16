package ru.lephant.learning.spring.SomeFirmWebFlow.workshop.service;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Workshop;
import java.util.List;

public interface WorkshopService {

    public List listWorkshop();

    public Workshop getWorkshopById(long id);

    public Workshop getLazyWorkshopById(long id);

    public void deleteWorkshop(long id);

    public void saveWorkshop(Workshop workshop);

}
