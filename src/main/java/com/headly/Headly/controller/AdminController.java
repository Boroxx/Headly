package com.headly.Headly.controller;

import com.headly.Headly.models.ApplicationModel;
import com.headly.Headly.services.ApplicationModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminController {

  @Autowired
  ApplicationModelService applicationModelService;

  @GetMapping("/applicationoverview")
  public String bewerbungen(Model model){
    model.addAttribute("applications",applicationModelService.loadApplicationModelList());
    return "applicationoverview";
  }
  @GetMapping("/admin/{applicationid}")
  public ResponseEntity<byte[]> getPDF1(@PathVariable String applicationid) {


    HttpHeaders headers = new HttpHeaders();

    headers.setContentType(MediaType.parseMediaType("application/pdf"));
    String filename = "Lebenslauf.pdf";
    headers.add("content-disposition", "inline;filename=" + filename);
    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
    ApplicationModel applicationModel= applicationModelService.getApplicatonModel(applicationid);

    ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(applicationModel.getLebenslauf_pdf(), headers, HttpStatus.OK);
    return response;
  }
}
