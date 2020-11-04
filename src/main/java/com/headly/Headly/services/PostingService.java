package com.headly.Headly.services;

import com.headly.Headly.models.Jobpost;
import com.headly.Headly.repos.JobPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostingService {

  @Autowired
  JobPostRepository jobPostRepository;

  @Autowired
  RegistrationService registrationService;

  public void registerPost(Jobpost jobpost){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if(auth==null){
      jobPostRepository.save(jobpost);
    }else{
      String username = ((UserDetails)auth.getPrincipal()).getUsername();
      int id = registrationService.findUserIdByUsername(username);
      jobpost.setUserid(id);
      jobPostRepository.save(jobpost);

    }



  }

  public Jobpost findById(int id){
    return jobPostRepository.findById(id);
  }
  public List<Jobpost> loadAllJobs(){
    return jobPostRepository.findAll();
  }

  public List<Jobpost> loadAllJobsById(int id){
    return jobPostRepository.findAllByUserid(id);
  }
}
