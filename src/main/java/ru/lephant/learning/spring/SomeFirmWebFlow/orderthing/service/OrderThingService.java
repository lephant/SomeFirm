package ru.lephant.learning.spring.SomeFirmWebFlow.orderthing.service;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.OrderThing;
import ru.lephant.learning.spring.SomeFirmWebFlow.enums.OrderState;
import ru.lephant.learning.spring.SomeFirmWebFlow.searchcriteries.OrderThingSearchCriteria;

import java.util.List;

public interface OrderThingService {

    public List listOrders(OrderThingSearchCriteria searchCriteria);

    public OrderThing getOrderById(long id);

    public void changeOrderState(OrderThing orderThing, OrderState state);

    public void makeViewed(OrderThing orderThing);

    public void createOrder(OrderThing orderThing);

}
