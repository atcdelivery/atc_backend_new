package com.atcdilivery.spring.jwt.mongodb.serviceImpl;

import com.atcdilivery.spring.jwt.mongodb.entity.CustomerDocument;
import com.atcdilivery.spring.jwt.mongodb.models.User;
import com.atcdilivery.spring.jwt.mongodb.repository.CustomerDocumentRepository;
import com.atcdilivery.spring.jwt.mongodb.repository.UserRepository;
import com.atcdilivery.spring.jwt.mongodb.service.StorageService;
import com.atcdilivery.spring.jwt.mongodb.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Date;
import java.util.Optional;

@Service
public class VerificationServiceImplementation implements VerificationService {

    @Autowired
    StorageService storageService;

    @Value("${document.path}")
    private Path rootLocation;

    @Autowired
    CustomerDocumentRepository customerDocumentRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public String saveCustomerDocument(MultipartFile document1, MultipartFile document2, String documentType1, String documentType2,String userId){
        Boolean response = storageService.store(document1,document2,documentType1,documentType2);
        if (response){
            CustomerDocument customerDocument = new CustomerDocument();
            customerDocument.setDocumentType1(documentType1);
            customerDocument.setDocumentType2(documentType2);
            customerDocument.setDocumentType1FileName(document1.getOriginalFilename());
            customerDocument.setDocumentType2FileName(document2.getOriginalFilename());
            customerDocument.setPath(rootLocation.toString());
            customerDocument.setCreateDate(new Date());
            customerDocument.setModifyDate(new Date());
            customerDocument.setUserId(userId);
            customerDocumentRepository.save(customerDocument);
            return "Documents Saved Successfully";
        }
        return null;
    }
    
    @Override
    public String verifyCustomer(String userId){
        Optional<User> user = userRepository.findById(userId);
        if (!ObjectUtils.isEmpty(user.get())){
            user.get().setVerified(true);
            userRepository.save(user.get());
            return "User Verified";
        }
        return "User Not Found";
    }
}
