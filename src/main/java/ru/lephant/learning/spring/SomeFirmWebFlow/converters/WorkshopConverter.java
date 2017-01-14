package ru.lephant.learning.spring.SomeFirmWebFlow.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Workshop;
import ru.lephant.learning.spring.SomeFirmWebFlow.workshop.service.WorkshopService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Component(value = "workshopConverterController")
@FacesConverter(value = "workshopConverter")
public class WorkshopConverter implements Converter {

    @Autowired
    WorkshopService workshopService;

    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if ("All".equals(s)) return null;
        Long id = Long.valueOf(s);
        if (id == 0) {
            Workshop workshop = new Workshop();
            workshop.setName("Главный склад");
            return workshop;
        }
        Workshop workshop = workshopService.getLazyWorkshopById(id);
        return workshop;
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null) return "All";
        return o.toString();
    }

}
