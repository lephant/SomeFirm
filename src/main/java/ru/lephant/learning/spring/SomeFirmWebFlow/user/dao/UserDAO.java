package ru.lephant.learning.spring.SomeFirmWebFlow.user.dao;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.User;

import java.util.List;

public interface UserDAO {

    public List listUser();

    public User getUserByUsername(String username);

}
