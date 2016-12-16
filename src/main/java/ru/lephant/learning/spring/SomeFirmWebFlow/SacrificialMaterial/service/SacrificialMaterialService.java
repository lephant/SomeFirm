package ru.lephant.learning.spring.SomeFirmWebFlow.SacrificialMaterial.service;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.SacrificialMaterialType;
import java.util.List;

public interface SacrificialMaterialService {

    public SacrificialMaterialType getSacrificialMaterialByPressmark(long pressmark);

    public List listSacrificialMaterial();

    public void saveSacrificialMaterial(SacrificialMaterialType sacrificialMaterial);

    public void deleteSacrificialMaterial(long pressmark);

}
