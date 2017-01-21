package ru.lephant.learning.spring.SomeFirmWebFlow.operation.dao;

import org.hibernate.exception.ConstraintViolationException;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Operation;
import ru.lephant.learning.spring.SomeFirmWebFlow.operation.FileUploadBean;

import java.util.List;

public interface OperationDAO {

    public Operation getOperationById(long id);

    public Operation getLazyOperationById(long id);

    public byte[] getPlanOfOperationById(long id);

    public List listOperation();

    public void deleteOperation(long id)
            throws ConstraintViolationException;

    public void saveOperation(Operation operation, FileUploadBean fileUploadBean)
            throws ConstraintViolationException;

}
