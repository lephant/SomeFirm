package ru.lephant.learning.spring.SomeFirmWebFlow.material.dao;

import org.hibernate.exception.ConstraintViolationException;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.SacrificialMaterialType;
import java.util.List;

public interface SacrificialMaterialDAO {

    public SacrificialMaterialType getSacrificialMaterialByPressmark(long pressmark);

    public List listSacrificialMaterial();

    public void createSacrificialMaterial(SacrificialMaterialType sacrificialMaterial)
            throws ConstraintViolationException, Exception;

    public void updateSacrificialMaterial(SacrificialMaterialType sacrificialMaterial)
            throws ConstraintViolationException, Exception;

    public void deleteSacrificialMaterial(long pressmark)
            throws ConstraintViolationException;

}
