package ru.lephant.learning.spring.SomeFirmWebFlow.user.service.impl;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Service;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.User;
import ru.lephant.learning.spring.SomeFirmWebFlow.user.dao.UserDAO;
import ru.lephant.learning.spring.SomeFirmWebFlow.user.service.UserService;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;


    @Override
    public List listUser() {
        return userDAO.listUser();
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    @Override
    public User getLazyUserByUsername(String username) {
        return userDAO.getLazyUserByUsername(username);
    }

    @Override
    public boolean registerUser(User user, MessageContext messageContext) {
        try {
            userDAO.registerUser(user);
            addRegisterCompleteMessage(messageContext, new MessageBuilder());
            return true;
        } catch (ConstraintViolationException e) {
            addRegisterNotCompleteMessage(messageContext, new MessageBuilder());
            return false;
        }
    }


    private void addRegisterCompleteMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Регистрация прошла успешно!")
                        .build()
                );
    }

    private void addRegisterNotCompleteMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .error()
                        .defaultText("Такое имя пользователя занято!")
                        .build()
                );
    }
}
