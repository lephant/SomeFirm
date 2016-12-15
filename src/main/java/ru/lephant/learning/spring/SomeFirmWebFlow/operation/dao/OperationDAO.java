package ru.lephant.learning.spring.SomeFirmWebFlow.operation.dao;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Operation;

import java.util.List;

public interface OperationDAO {

    public Operation getOperationById(long id);

    public List listOperation();

    public void deleteOperation(long id);

    public void saveOperation(Operation operation);

}