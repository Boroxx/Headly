package com.headly.Headly.controller;

import com.headly.Headly.dto.SearchString;
import com.headly.Headly.models.Jobpost;
import com.headly.Headly.services.ProfessionService;
import com.headly.Headly.services.SearchJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.annotation.SessionScope;

import javax.validation.Valid;
import java.util.List;


@Controller
public class SearchController {


  @Autowired
  SearchJobService searchJobService;

  @Autowired
  ProfessionService professionService;

  private List<Jobpost> searchList;
  private boolean sendSearchReq= false;


  @GetMapping("/search")
  @Transactional
  public String search(Model model){
    String hits="";
    model.addAttribute("searchDto", new SearchString());
    model.addAttribute("professions",professionService.getAllProfessions() );

    if(searchList!=null){
      model.addAttribute("searchList",searchList);
      searchList = null;
      sendSearchReq = false;

    }else if(sendSearchReq){
      hits="Keine Treffer!";
      model.addAttribute("hits", hits);
      sendSearchReq=false;
    }


    return"search";
  }

  @Transactional
  @PostMapping("/search")
  public String search(@Valid @ModelAttribute SearchString searchString, BindingResult bindingResult){
    if(bindingResult.hasErrors()){
      return "redirect:/search";
    }

     sendSearchReq = true;
    String substring = searchString.getSubString();
    String upperedString = substring.substring(0,1).toUpperCase() + substring.substring(1);
    searchList = searchJobService.findAllJobsByGivenArguments(upperedString);
    return"redirect:/search";
  }

  @Transactional
  @PostMapping("/search/branch")
  public String searchAfterBranch(@ModelAttribute SearchString searchString){
    sendSearchReq = true;
    String profession = searchString.getSubString();
    searchList = searchJobService.findAllJobsByContainingProfession(profession);

    return"redirect:/search";
  }
}
