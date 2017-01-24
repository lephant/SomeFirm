package ru.lephant.learning.spring.SomeFirmWebFlow.storage.employe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageEmploye;
import ru.lephant.learning.spring.SomeFirmWebFlow.storage.employe.dao.StorageEmployeDAO;
import ru.lephant.learning.spring.SomeFirmWebFlow.storage.employe.service.StorageEmployeService;

import java.util.List;

@Service("storageEmployeService")
public class StorageEmployeServiceImpl implements StorageEmployeService {

    @Autowired
    StorageEmployeDAO storageEmployeDAO;


    @Override
    public List listStorageEmploye() {
        return storageEmployeDAO.listStorageEmploye();
    }

    @Override
    public StorageEmploye getStorageEmployeById(long id) {
        return storageEmployeDAO.getStorageEmployeById(id);
    }

    @Override
    public void saveStorageEmploye(StorageEmploye storageEmploye) {
        storageEmployeDAO.saveStorageEmploye(storageEmploye);
    }

    @Override
    public void deleteStorageEmploye(long id) {
        storageEmployeDAO.deleteStorageEmploye(id);
    }

}
