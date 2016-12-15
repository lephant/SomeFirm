package ru.lephant.learning.spring.SomeFirmWebFlow.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Workshop;
import ru.lephant.learning.spring.SomeFirmWebFlow.workshop.service.WorkshopService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Controller(value = "workshopConverterController")
@FacesConverter(value = "workshopConverter")
public class WorkshopConverter implements Converter {

    @Autowired
    WorkshopService workshopService;

    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Workshop workshop = workshopService.getWorkshopById(Long.valueOf(s));
        return null;
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return o.toString();
    }

}