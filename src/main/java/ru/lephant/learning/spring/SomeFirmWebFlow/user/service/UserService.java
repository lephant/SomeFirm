package ru.lephant.learning.spring.SomeFirmWebFlow.user.service;

import org.springframework.binding.message.MessageContext;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.User;

import java.util.List;

public interface UserService {

    public List listUser();

    public User getUserByUsername(String username);

    public User getLazyUserByUsername(String username);

    public boolean registerUser(User user, MessageContext messageContext);

}
