package com.headly.Headly.repos;

import com.headly.Headly.models.Jobpost;
import com.headly.Headly.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

  User findByEmail(String email);

}
