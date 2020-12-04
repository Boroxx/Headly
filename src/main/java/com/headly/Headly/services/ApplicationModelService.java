package com.headly.Headly.services;


import com.headly.Headly.dto.ApplicationDto;
import com.headly.Headly.dto.ApplicationOverviewDto;
import com.headly.Headly.models.ApplicationModel;
import com.headly.Headly.models.Jobpost;
import com.headly.Headly.models.User;
import com.headly.Headly.repos.ApplicationModelRepository;
import com.headly.Headly.repos.JobPostRepository;
import com.headly.Headly.repos.UserRepository;
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
  @Autowired
  private JobPostRepository jobPostRepository;

  @Autowired
  private UserRepository userRepository;

  public void registerApplication(User user, ApplicationDto applicationDto,String jobid) throws IOException {
    ApplicationModel applicationModel = ApplicationModel.builder()
            .jobid(jobid).lebenslauf_pdf(applicationDto.getFile().getBytes()).userid(user.getId()).build();
    applicationModelRepository.save(applicationModel);
  }

  public List<ApplicationModel> loadApplicationModelList(){

    return applicationModelRepository.findAll();
  }

  /*Creates a ApplicationOverviewModelList for serving Data to /applicationoverview */

  public List<ApplicationOverviewDto> loadApplicationModelListForApModelDto(){
    List<ApplicationModel> models = applicationModelRepository.findAll();
    List<ApplicationOverviewDto>applicationOverviewDtos = new ArrayList<>();
    for(ApplicationModel model : models){
      String jobname = jobPostRepository.findById(Integer.parseInt(model.getJobid())).getJobname();
      User user = userRepository.findById(model.getUserid());

      if(jobname!=null && user!=null){
        String userfullname = user.getFirstname() + " "+ user.getLastname();
        String email = user.getEmail();

        ApplicationOverviewDto applicationOverviewDto = ApplicationOverviewDto.builder()
                .applicationid(model.getApplicationid().toString())
                .jobid(model.getJobid())
                .userid(String.valueOf(model.getUserid())).jobname(jobname).userfullname(userfullname).usermail(email).build();
        applicationOverviewDtos.add(applicationOverviewDto);
      }



    }
    return applicationOverviewDtos;
  }


  public List<ApplicationOverviewDto> bakeApplicationOverviewList() {
    return null;
  }

  public ApplicationModel getApplicatonModel(String applicationid){


    return applicationModelRepository.findByApplicationid(UUID.fromString(applicationid));
  }



}
