package ru.lephant.learning.spring.SomeFirmWebFlow.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Thing;
import ru.lephant.learning.spring.SomeFirmWebFlow.thing.service.ThingService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Component("thingConverterController")
@FacesConverter(value = "thingConverterConverter")
public class ThingConverter implements Converter {

    @Autowired
    ThingService thingService;

    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Thing thing = thingService.getThingByPressmark(Long.valueOf(s));
        return thing;
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return o.toString();
    }
}
