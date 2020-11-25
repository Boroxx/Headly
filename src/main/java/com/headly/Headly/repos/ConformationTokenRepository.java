package com.headly.Headly.repos;

import com.headly.Headly.models.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConformationTokenRepository extends JpaRepository<ConfirmationToken,Long> {
  ConfirmationToken findByConfirmationToken(String conf);
}
