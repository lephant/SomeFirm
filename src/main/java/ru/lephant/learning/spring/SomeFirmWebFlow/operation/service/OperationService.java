package ru.lephant.learning.spring.SomeFirmWebFlow.operation.service;

import org.springframework.binding.message.MessageContext;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Operation;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.OperationSacrificialMaterial;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.OperationTool;
import ru.lephant.learning.spring.SomeFirmWebFlow.operation.FileUploadBean;

import java.util.List;

public interface OperationService {

    public Operation getOperationById(long id);

    public Operation getLazyOperationById(long id);

    public byte[] getPlanOfOperationById(long id);

    public List listOperation();

    public boolean deleteOperation(long id, MessageContext messageContext);

    public void saveOperation(Operation operation, FileUploadBean fileUploadBean, MessageContext messageContext);

    public void addToolToList(Operation operation, OperationTool tool,
                              MessageContext messageContext);

    public void removeToolFromList(Operation operation, OperationTool tool,
                                   MessageContext messageContext);

    public void addMaterialToList(Operation operation, OperationSacrificialMaterial material,
                                  MessageContext messageContext);

    public void removeMaterialFromList(Operation operation, OperationSacrificialMaterial material,
                                       MessageContext messageContext);

}
