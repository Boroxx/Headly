package com.headly.Headly.services;

import com.headly.Headly.ErrorHandling.TemplateLogger;
import com.headly.Headly.models.Profession;
import com.headly.Headly.repos.ProfessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionService {
  TemplateLogger templateError = new TemplateLogger();

  @Autowired
  public ProfessionRepository professionRepository;


  public void registerProfession(Profession profession){
    if(professionRepository.getById(profession.getId())==null){
        professionRepository.save(profession);
    }


  }

  public List<Profession> getAllProfessions(){
    return professionRepository.findAll();
  }
}
