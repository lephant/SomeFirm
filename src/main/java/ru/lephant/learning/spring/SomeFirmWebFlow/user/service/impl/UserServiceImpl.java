package ru.lephant.learning.spring.SomeFirmWebFlow.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.User;
import ru.lephant.learning.spring.SomeFirmWebFlow.user.dao.UserDAO;
import ru.lephant.learning.spring.SomeFirmWebFlow.user.service.UserService;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;


    @Override
    @Transactional(readOnly = true)
    public List listUser() {
        return userDAO.listUser();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }
}
