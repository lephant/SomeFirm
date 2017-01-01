package ru.lephant.learning.spring.SomeFirmWebFlow.thing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Thing;
import ru.lephant.learning.spring.SomeFirmWebFlow.thing.dao.ThingDAO;
import ru.lephant.learning.spring.SomeFirmWebFlow.thing.service.ThingService;

import java.util.List;

@Service("thingService")
public class ThingServiceImpl implements ThingService {

    @Autowired
    ThingDAO thingDAO;


    @Transactional(readOnly = true)
    public List listThing() {
        return thingDAO.listThing();
    }

    @Transactional(readOnly = true)
    public Thing getThingByPressmark(long pressmark) {
        return thingDAO.getThingByPressmark(pressmark);
    }

}
