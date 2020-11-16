package com.headly.Headly.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jobpost {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  private int userid;


  @CreationTimestamp
  private LocalDateTime createDateTime;
  @Column
  private String jobname;

  private String profession;

  private String salary;

  private String location;
  //Type of Employment
  private String toe;

  @Column
  @Lob
  private String description;



}
