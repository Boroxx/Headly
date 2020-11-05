package com.headly.Headly.repos;

import com.headly.Headly.models.Profession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionRepository extends JpaRepository<Profession,Long> {
  Profession getById(int id);

}
