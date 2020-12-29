package com.headly.Headly.repos;

import com.headly.Headly.models.ApplicationModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplicationModelRepository extends JpaRepository<ApplicationModel, Long> {
  ApplicationModel findByApplicationid(UUID applicationid);

  ApplicationModel findByUserid(int id);
}
