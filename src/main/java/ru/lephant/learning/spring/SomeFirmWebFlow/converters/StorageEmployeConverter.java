package ru.lephant.learning.spring.SomeFirmWebFlow.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageEmploye;
import ru.lephant.learning.spring.SomeFirmWebFlow.storage.employe.service.StorageEmployeService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Controller("storageEmployeConverterController")
@FacesConverter(value = "storageEmployeConverter")
public class StorageEmployeConverter implements Converter {

    @Autowired
    StorageEmployeService storageEmployeService;

    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        StorageEmploye storageEmploye = storageEmployeService.getStorageEmployeById(Long.valueOf(s));
        return storageEmploye;
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return o.toString();
    }
}
