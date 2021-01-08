package com.headly.Headly.services;

import com.headly.Headly.controller.AdminController;
import com.headly.Headly.models.Lebenslauf;
import com.headly.Headly.models.User;
import com.headly.Headly.repos.UploadLebenslaufRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UploadLebenslaufService {

    @Autowired
    UploadLebenslaufRepository uploadLebenslaufRepository;

    Logger logger = LoggerFactory.getLogger(UploadLebenslaufService.class);


    public void registerLebenslauf(User user, MultipartFile multipartFile){
        if(uploadLebenslaufRepository.findByUserId(user.getId()) ==null){
            try {
                Lebenslauf lebenslauf = Lebenslauf.builder().lebenslauf_pdf(multipartFile.getBytes()).user(user).build();
                uploadLebenslaufRepository.save(lebenslauf);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            logger.error("Lebenslauf wurde schon hochgeladen");
        }

    }

    public void deleteLebenslauf(User user) {

            Lebenslauf lebenslauf = uploadLebenslaufRepository.findByUserId(user.getId());
            uploadLebenslaufRepository.deleteById(lebenslauf.getId());



    }

    public Lebenslauf findLebenslaufByUserId(User user){
        return uploadLebenslaufRepository.findByUserId(user.getId());
    }
}
