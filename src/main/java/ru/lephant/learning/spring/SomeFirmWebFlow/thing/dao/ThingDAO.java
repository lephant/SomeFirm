package ru.lephant.learning.spring.SomeFirmWebFlow.thing.dao;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Thing;

import java.util.List;

public interface ThingDAO {

    public List listThing();

    public Thing getThingByPressmark(long pressmark);

}
