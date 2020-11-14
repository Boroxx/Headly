package com.headly.Headly.ErrorHandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateError {
  public String error;
  public boolean exists;

}
