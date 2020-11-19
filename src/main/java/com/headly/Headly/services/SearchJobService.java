package com.headly.Headly.services;


import com.headly.Headly.models.Jobpost;
import com.headly.Headly.repos.JobPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchJobService {

  @Autowired
  JobPostRepository jobPostRepository;


  public List<Jobpost> findAllJobsByGivenArguments(String substring){
    return jobPostRepository.findAllByJobnameIsContaining(substring);
  }

  public List<Jobpost> findAllJobsByContainingProfession(String substring){
    return jobPostRepository.findAllByProfessionIsContaining(substring);
  }

}
