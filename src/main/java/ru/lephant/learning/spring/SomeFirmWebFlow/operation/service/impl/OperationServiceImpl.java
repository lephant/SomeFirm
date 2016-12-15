package ru.lephant.learning.spring.SomeFirmWebFlow.operation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Operation;
import ru.lephant.learning.spring.SomeFirmWebFlow.operation.dao.OperationDAO;
import ru.lephant.learning.spring.SomeFirmWebFlow.operation.service.OperationService;

import java.io.Serializable;
import java.util.List;

@Service("operationService")
public class OperationServiceImpl implements OperationService, Serializable {

    @Autowired
    private OperationDAO operationDAO;

    @Transactional(readOnly = true)
    public Operation getOperationById(long id) {
        return operationDAO.getOperationById(id);
    }

    @Transactional(readOnly = true)
    public List listOperation() {
        return operationDAO.listOperation();
    }

    @Transactional
    public void deleteOperation(long id) {
        operationDAO.deleteOperation(id);
    }

    @Transactional
    public void saveOperation(Operation operation) {
        operationDAO.saveOperation(operation);
    }

}
