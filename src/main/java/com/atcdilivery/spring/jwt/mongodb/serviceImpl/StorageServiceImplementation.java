package com.atcdilivery.spring.jwt.mongodb.serviceImpl;

import com.atcdilivery.spring.jwt.mongodb.service.StorageService;
import com.atcdilivery.spring.jwt.mongodb.utilities.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class StorageServiceImplementation implements StorageService {

    @Value("${document.path}")
    private Path rootLocation;

    @Override
    public Boolean store(MultipartFile file1,MultipartFile file2,String documentName1,String documentName2) {
        try {
            if (file1.isEmpty()) {
                System.out.println("Files Not Present or File Name is empty");
                return false;
            }else {
                File fileOne = new File(rootLocation + "/" + file1.getOriginalFilename());
                Files.copy(file1.getInputStream(), this.rootLocation.resolve(file1.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);

                File fileTwo = new File(rootLocation + "/" + file2.getOriginalFilename());
                Files.copy(file1.getInputStream(), this.rootLocation.resolve(file2.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
                return true;
            }
        } catch (IOException e) {
            System.out.println("Normal IOException");
            System.out.println("Message " + e.getMessage());
            System.out.println("Cause " + e.getCause());
            throw new StorageException("Failed to store file " + file1.getOriginalFilename(), e);
        }
        catch (SecurityException e) {
            // TODO: handle exception
            System.out.println("Normal SecurityException");
            System.out.println("Message " + e.getMessage());
            System.out.println("Cause " + e.getCause());
        }
        catch (Exception e) {
            System.out.println("Normal Exception");
            System.out.println("Message " + e.getMessage());
            System.out.println("Cause " + e.getCause());
        }

        return null;
    }
}
