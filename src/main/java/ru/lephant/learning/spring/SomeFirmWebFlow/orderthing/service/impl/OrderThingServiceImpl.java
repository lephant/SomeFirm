package ru.lephant.learning.spring.SomeFirmWebFlow.orderthing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.OrderThing;
import ru.lephant.learning.spring.SomeFirmWebFlow.enums.OrderState;
import ru.lephant.learning.spring.SomeFirmWebFlow.orderthing.dao.OrderThingDAO;
import ru.lephant.learning.spring.SomeFirmWebFlow.orderthing.service.OrderThingService;
import ru.lephant.learning.spring.SomeFirmWebFlow.searchcriteries.OrderThingSearchCriteria;

import java.util.List;

@Service("orderThingService")
public class OrderThingServiceImpl implements OrderThingService {

    @Autowired
    OrderThingDAO orderThingDAO;

    @Override
    @Transactional(readOnly = true)
    public List listOrders(OrderThingSearchCriteria searchCriteria) {
        return orderThingDAO.listOrders(searchCriteria);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderThing getOrderById(long id) {
        return orderThingDAO.getOrderById(id);
    }

    @Override
    @Transactional
    public void changeOrderState(OrderThing orderThing, OrderState state) {
        orderThing.setState(state);
        orderThingDAO.changeOrderState(orderThing);
    }

    @Override
    @Transactional
    public void makeViewed(OrderThing orderThing) {
        if (orderThing.getState() == OrderState.NOT_VIEWED) {
            orderThing.setState(OrderState.VIEWED);
            orderThingDAO.changeOrderState(orderThing);
        }
    }

    @Override
    @Transactional
    public void createOrder(OrderThing orderThing) {
        orderThing.setState(OrderState.NOT_VIEWED);
        orderThingDAO.createOrder(orderThing);
    }
}
