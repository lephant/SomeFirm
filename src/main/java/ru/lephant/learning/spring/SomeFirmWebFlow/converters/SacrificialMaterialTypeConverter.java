package ru.lephant.learning.spring.SomeFirmWebFlow.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.SacrificialMaterialType;
import ru.lephant.learning.spring.SomeFirmWebFlow.material.service.SacrificialMaterialService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Component(value = "sacrificialMaterialTypeConverterController")
@FacesConverter(value = "sacrificialMaterialTypeConverter")
public class SacrificialMaterialTypeConverter implements Converter {

    @Autowired
    SacrificialMaterialService sacrificialMaterialService;

    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        SacrificialMaterialType sacrificialMaterial = sacrificialMaterialService.getSacrificialMaterialByPressmark(Long.valueOf(s));
        return sacrificialMaterial;
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return o.toString();
    }

}
