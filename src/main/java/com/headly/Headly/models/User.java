package com.headly.Headly.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_dat")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private boolean isEnabled;
  private String companyname;
  private String email;
  private String contactperson;
  private String street;
  private String housenumber;
  private String zipcode;
  private String city;
  private String phonenumber;
  private String password;
  private String role;
  private String firstname;
  private String lastname;



}
