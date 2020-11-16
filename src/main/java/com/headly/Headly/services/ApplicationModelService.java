package com.headly.Headly.services;


import com.headly.Headly.dto.ApplicationDto;
import com.headly.Headly.dto.ApplicationOverviewDto;
import com.headly.Headly.models.ApplicationModel;
import com.headly.Headly.models.User;
import com.headly.Headly.repos.ApplicationModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ApplicationModelService {

  @Autowired
  private ApplicationModelRepository applicationModelRepository;

  public void registerApplication(User user, ApplicationDto applicationDto,String jobid) throws IOException {
    ApplicationModel applicationModel = ApplicationModel.builder()
            .jobid(jobid).lebenslauf_pdf(applicationDto.getFile().getBytes()).userid(user.getId()).build();
    applicationModelRepository.save(applicationModel);
  }

  public List<ApplicationModel> loadApplicationModelList(){
    return applicationModelRepository.findAll();
  }


  public List<ApplicationOverviewDto> bakeApplicationOverviewList() {
    return null;
  }

  public ApplicationModel getApplicatonModel(String applicationid){


    return applicationModelRepository.findByApplicationid(UUID.fromString(applicationid));
  }



}
