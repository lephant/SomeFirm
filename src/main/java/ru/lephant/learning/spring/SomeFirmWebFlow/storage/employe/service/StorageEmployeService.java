package ru.lephant.learning.spring.SomeFirmWebFlow.storage.employe.service;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageEmploye;
import java.util.List;

public interface StorageEmployeService {

    public List listStorageEmploye();

    public StorageEmploye getStorageEmployeById(long id);

    public void saveStorageEmploye(StorageEmploye storageEmploye);

    public void deleteStorageEmploye(long id);

}
