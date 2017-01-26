package ru.lephant.learning.spring.SomeFirmWebFlow.orderthing.dao;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.OrderThing;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.User;
import ru.lephant.learning.spring.SomeFirmWebFlow.searchcriteries.OrderThingSearchCriteria;

import java.util.List;

public interface OrderThingDAO {

    public List listOrders(OrderThingSearchCriteria searchCriteria);

    public List listOrders(OrderThingSearchCriteria searchCriteria, User user);

    public OrderThing getOrderById(long id);

    public void changeOrderState(OrderThing orderThing);

    public void createOrder(OrderThing orderThing);

}