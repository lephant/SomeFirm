package ru.lephant.learning.spring.SomeFirmWebFlow.user.service;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.User;

import java.util.List;

public interface UserService {

    public List listUser();

    public User getUserByUsername(String username);

}
