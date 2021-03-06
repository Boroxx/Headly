package com.headly.Headly.controller;

import com.headly.Headly.models.ApplicationModel;
import com.headly.Headly.models.Lebenslauf;
import com.headly.Headly.services.ApplicationModelService;
import com.headly.Headly.services.UploadLebenslaufService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AdminController {
  Logger logger = LoggerFactory.getLogger(AdminController.class);

  @Autowired
  ApplicationModelService applicationModelService;
  @Autowired
  UploadLebenslaufService uploadLebenslaufService;


  @GetMapping("/applicationoverview")
  public String bewerbungen(Model model){
    List<Lebenslauf> lebenslaufList = uploadLebenslaufService.findAll();
    model.addAttribute("lebenslaufList", lebenslaufList);
    model.addAttribute("applications",applicationModelService.loadApplicationModelListForApModelDto());
    logger.trace("Filter ApplicationModelDto's");
    return "applicationoverview";
  }

  @Transactional
  @GetMapping("/admin/{applicationid}")
  public ResponseEntity<byte[]> getPDF1(@PathVariable String applicationid) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.parseMediaType("application/pdf"));
    String filename = "Lebenslauf.pdf";
    headers.add("content-disposition", "inline;filename=" + filename);
    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
    ApplicationModel applicationModel= applicationModelService.getApplicatonModel(applicationid);
    if(applicationModel.getLebenslauf_pdf()==null){
      logger.error("Konnte PDF-File mit Application-Id" + applicationid +"nicht in Datenbank finden");
      return new ResponseEntity<byte[]>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }else{
      return new ResponseEntity<byte[]>(applicationModel.getLebenslauf_pdf(), headers, HttpStatus.OK);
    }

  }

  @Transactional
  @GetMapping("/lebenslauf/{lebenslaufid}")
  public ResponseEntity<byte[]> getLebenslauf(@PathVariable String lebenslaufid) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.parseMediaType("application/pdf"));
    String filename = "Lebenslauf.pdf";
    headers.add("content-disposition", "inline;filename=" + filename);
    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
    Lebenslauf lebenslauf = uploadLebenslaufService.findById(Long.parseLong(lebenslaufid));
    if(lebenslauf.getLebenslauf_pdf()==null){
      logger.error("Konnte PDF-File mit Application-Id" + lebenslaufid +"nicht in Datenbank finden");
      return new ResponseEntity<byte[]>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }else{
      return new ResponseEntity<byte[]>(lebenslauf.getLebenslauf_pdf(), headers, HttpStatus.OK);
    }

  }
}
