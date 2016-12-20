package ru.lephant.learning.spring.SomeFirmWebFlow.material.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lephant.learning.spring.SomeFirmWebFlow.material.dao.SacrificialMaterialDAO;
import ru.lephant.learning.spring.SomeFirmWebFlow.material.service.SacrificialMaterialService;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.SacrificialMaterialType;

import java.util.List;

@Service("sacrificialMaterialService")
public class SacrificialMaterialServiceImpl implements SacrificialMaterialService {

    @Autowired
    SacrificialMaterialDAO sacrificialMaterialDAO;

    @Transactional(readOnly = true)
    public SacrificialMaterialType getSacrificialMaterialByPressmark(long pressmark) {
        return sacrificialMaterialDAO.getSacrificialMaterialByPressmark(pressmark);
    }

    @Transactional(readOnly = true)
    public List listSacrificialMaterial() {
        return sacrificialMaterialDAO.listSacrificialMaterial();
    }

    @Transactional
    public void saveSacrificialMaterial(SacrificialMaterialType sacrificialMaterial) {
        sacrificialMaterialDAO.saveSacrificialMaterial(sacrificialMaterial);
    }

    @Transactional
    public void deleteSacrificialMaterial(long pressmark) {
        sacrificialMaterialDAO.deleteSacrificialMaterial(pressmark);
    }
}
