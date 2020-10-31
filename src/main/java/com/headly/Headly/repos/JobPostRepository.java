package com.headly.Headly.repos;

import com.headly.Headly.models.Jobpost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface JobPostRepository extends JpaRepository<Jobpost,Long> {
}
