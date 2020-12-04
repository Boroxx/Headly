package com.headly.Headly.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationOverviewDto {
  public String applicationid;
  public String userid;
  public String jobid;

  public String jobname;
  public String usermail;
  public String userfullname;
}
