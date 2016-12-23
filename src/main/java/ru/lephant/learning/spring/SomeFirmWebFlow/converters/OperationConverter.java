package ru.lephant.learning.spring.SomeFirmWebFlow.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Operation;
import ru.lephant.learning.spring.SomeFirmWebFlow.operation.service.OperationService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Controller("operationConverterController")
@FacesConverter(value = "operationConverter")
public class OperationConverter implements Converter{

    @Autowired
    OperationService operationService;

    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Operation operation = operationService.getLazyOperationById(Long.valueOf(s));
        return operation;
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return o.toString();
    }
}
