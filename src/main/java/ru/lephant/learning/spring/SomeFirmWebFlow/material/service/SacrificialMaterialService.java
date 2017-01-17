package ru.lephant.learning.spring.SomeFirmWebFlow.material.service;

import org.springframework.binding.message.MessageContext;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.SacrificialMaterialType;
import java.util.List;

public interface SacrificialMaterialService {

    public SacrificialMaterialType getSacrificialMaterialByPressmark(long pressmark);

    public List listSacrificialMaterial();

    public void saveSacrificialMaterial(SacrificialMaterialType sacrificialMaterial, MessageContext messageContext,
                                        boolean editable);

    public void deleteSacrificialMaterial(long pressmark, MessageContext messageContext);

}
