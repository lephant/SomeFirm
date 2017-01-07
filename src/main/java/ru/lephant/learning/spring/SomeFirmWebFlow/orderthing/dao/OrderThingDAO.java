package ru.lephant.learning.spring.SomeFirmWebFlow.orderthing.dao;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.OrderThing;

import java.util.List;

public interface OrderThingDAO {

    public List listOrders();

    public OrderThing getOrderById(long id);

    public void changeOrderState(OrderThing orderThing);

    public void createOrder(OrderThing orderThing);

}