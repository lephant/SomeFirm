package ru.lephant.learning.spring.SomeFirmWebFlow.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.ToolType;
import ru.lephant.learning.spring.SomeFirmWebFlow.tool.service.ToolService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Component(value = "toolTypeConverterController")
@FacesConverter(value = "toolTypeConverter")
public class ToolTypeConverter implements Converter {

    @Autowired
    ToolService toolService;

    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        ToolType tool = toolService.getToolByPressmark(Long.valueOf(s));
        return tool;
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return o.toString();
    }
}
