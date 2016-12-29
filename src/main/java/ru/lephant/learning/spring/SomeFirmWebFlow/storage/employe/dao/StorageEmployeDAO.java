package ru.lephant.learning.spring.SomeFirmWebFlow.storage.employe.dao;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageEmploye;

import java.util.List;

public interface StorageEmployeDAO {

    public List listStorageEmploye();

    public StorageEmploye getStorageEmployeById(long id);

    public void saveStorageEmploye(StorageEmploye storageEmploye);

    public void deleteStorageEmploye(long id);

}
