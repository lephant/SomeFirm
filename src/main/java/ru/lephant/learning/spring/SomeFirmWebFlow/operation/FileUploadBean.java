package ru.lephant.learning.spring.SomeFirmWebFlow.operation;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class FileUploadBean implements Serializable {

    private byte[] data;


    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile file = event.getFile();

        data = new byte[(int)file.getSize()];

        try(InputStream in = file.getInputstream()) {
            while (in.read(data) != -1) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        data = null;
    }


    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
