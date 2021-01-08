package com.headly.Headly.repos;

import com.headly.Headly.models.Lebenslauf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UploadLebenslaufRepository extends JpaRepository<Lebenslauf,Long> {

    Lebenslauf findByUserId(int id);
    void deleteById(long id);
}
