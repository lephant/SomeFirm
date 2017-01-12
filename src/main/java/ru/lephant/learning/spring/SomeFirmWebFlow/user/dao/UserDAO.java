package ru.lephant.learning.spring.SomeFirmWebFlow.user.dao;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.User;

import java.util.List;
import java.util.Set;

public interface UserDAO {

    public List listUser();

    public User getUserByUsername(String username);

    public List<User> getAnotherWorkers(Set<User> engagedWorkers);
}
