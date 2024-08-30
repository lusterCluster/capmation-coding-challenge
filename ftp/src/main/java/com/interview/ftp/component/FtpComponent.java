package com.interview.ftp.component;

import com.interview.ftp.constant.IFilePaths;
import com.interview.ftp.constant.IFtpProps;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FtpComponent {
    public  void uploadRMJson() {

        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(IFtpProps.SERVER, IFtpProps.PORT);
            ftpClient.login(IFtpProps.USER, IFtpProps.PASSWORD);
            ftpClient.enterLocalPassiveMode();

            // Configurar el tipo de archivo a ser transferido
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

            // Convertir el String a un InputStream
            File xmlFile = new File(IFilePaths.RM_JSON);
            InputStream inputStream = new FileInputStream(xmlFile);
           boolean done = ftpClient.storeFile(IFtpProps.RM_JSON_PATH, inputStream);
            inputStream.close();

            if (done) {
                System.out.println("El archivo fue subido exitosamente.");
            } else {
                System.out.println("Hubo un error al subir el archivo.");
            }

            ftpClient.logout();
            ftpClient.disconnect();

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}

