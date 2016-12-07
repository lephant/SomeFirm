package ru.lephant.learning.spring.SomeFirmWebFlow.operation.service;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Operation;

import java.util.List;

public interface OperationService {

    public Operation getOperationById(long id);

    public List listOperation();

    public void deleteOperation(long id);

    public void saveOperation(Operation operation);

}
