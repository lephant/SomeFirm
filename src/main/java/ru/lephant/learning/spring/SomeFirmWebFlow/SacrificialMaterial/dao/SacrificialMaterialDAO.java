package ru.lephant.learning.spring.SomeFirmWebFlow.SacrificialMaterial.dao;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.SacrificialMaterialType;
import java.util.List;

public interface SacrificialMaterialDAO {

    public SacrificialMaterialType getSacrificialMaterialByPressmark(long pressmark);

    public List listSacrificialMaterial();

    public void saveSacrificialMaterial(SacrificialMaterialType sacrificialMaterial);

    public void deleteSacrificialMaterial(long pressmark);

}
