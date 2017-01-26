package ru.lephant.learning.spring.SomeFirmWebFlow.user.dao;

import org.hibernate.exception.ConstraintViolationException;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.User;

import java.util.List;
import java.util.Set;

public interface UserDAO {

    public List listUser();

    public User getUserByUsername(String username);

    public User getLazyUserByUsername(String username);

    public List<User> getAnotherWorkers(Set<User> engagedWorkers);

    public void registerUser(User user)
            throws ConstraintViolationException;

}
