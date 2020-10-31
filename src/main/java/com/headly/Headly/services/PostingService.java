package com.headly.Headly.services;

import com.headly.Headly.models.Jobpost;
import com.headly.Headly.repos.JobPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostingService {

  @Autowired
  JobPostRepository jobPostRepository;

  public void registerPost(Jobpost jobpost){
    jobPostRepository.save(jobpost);
  }

  public List<Jobpost> loadAllJobs(){
    return jobPostRepository.findAll();
  }
}
