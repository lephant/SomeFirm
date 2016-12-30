package ru.lephant.learning.spring.SomeFirmWebFlow.thing.service;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Thing;

import java.util.List;

public interface ThingService {

    public List listThing();

    public Thing getThingByPressmark(long pressmark);

}
