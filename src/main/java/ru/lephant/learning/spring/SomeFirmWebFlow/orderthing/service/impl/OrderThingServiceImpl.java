package ru.lephant.learning.spring.SomeFirmWebFlow.orderthing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Service;
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
    public List listOrders(OrderThingSearchCriteria searchCriteria) {
        return orderThingDAO.listOrders(searchCriteria);
    }

    @Override
    public OrderThing getOrderById(long id) {
        return orderThingDAO.getOrderById(id);
    }

    @Override
    public void changeOrderState(OrderThing orderThing, OrderState state, MessageContext messageContext) {
        orderThing.setState(state);
        orderThingDAO.changeOrderState(orderThing);
        addStateChangeMessage(messageContext, new MessageBuilder(), orderThing.getState());
    }

    @Override
    public void makeViewed(OrderThing orderThing, MessageContext messageContext) {
        if (orderThing.getState() == OrderState.NOT_VIEWED) {
            orderThing.setState(OrderState.VIEWED);
            orderThingDAO.changeOrderState(orderThing);
            addStateChangeMessage(messageContext, new MessageBuilder(), orderThing.getState());
        }
    }

    @Override
    public boolean createOrder(OrderThing orderThing, MessageContext messageContext) {
        try {
            orderThing.setState(OrderState.NOT_VIEWED);
            orderThingDAO.createOrder(orderThing);
            addCreateMessage(messageContext, new MessageBuilder());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void addCreateTeamMessage(MessageContext messageContext) {
        MessageBuilder builder = new MessageBuilder();
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Наряд для данного заказа успешно создан!")
                        .build()
                );
    }


    private void addStateChangeMessage(MessageContext messageContext, MessageBuilder builder, OrderState state) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Состояние заказа сменилось на: " + state.getStateDescription())
                        .build()
                );
    }

    private void addCreateMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Ваша заявка успешно создана. Ожидайте ответа.")
                        .build()
                );
    }
}
