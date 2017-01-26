package ru.lephant.learning.spring.SomeFirmWebFlow.thing.dao;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Thing;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.User;

import java.util.List;

public interface ThingDAO {

    public List listThing();

    public List listThing(User user);

    public Thing getThingByPressmark(long pressmark);

}
