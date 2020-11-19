package com.headly.Headly.repos;

import com.headly.Headly.models.Jobpost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JobPostRepository extends JpaRepository<Jobpost,Long> {
  List<Jobpost> findAllByUserid(int id);
  Jobpost findById(int id);
  List<Jobpost> findAllByJobnameIsContaining(String s);
  List<Jobpost> findAllByProfessionIsContaining(String s);
}
