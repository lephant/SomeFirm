package ru.lephant.learning.spring.SomeFirmWebFlow.thing.service;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Thing;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.User;

import java.util.List;

public interface ThingService {

    public List listThing();

    public List listThing(User user);

    public Thing getThingByPressmark(long pressmark);

}
