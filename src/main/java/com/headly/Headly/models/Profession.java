package com.headly.Headly.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profession {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  public int id;

  public String professionname;

}
