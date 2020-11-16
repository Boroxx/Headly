package com.headly.Headly.controller;

import com.headly.Headly.dto.SearchString;
import com.headly.Headly.models.Jobpost;
import com.headly.Headly.services.SearchJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SearchController {


  @Autowired
  SearchJobService searchJobService;

  private List<Jobpost> searchList;
  private boolean posted= false;


  @GetMapping("/search")
  @Transactional
  public String search(Model model){
    String hits="";
    model.addAttribute("searchDto", new SearchString());

    if(searchList!=null){
      model.addAttribute("searchList",searchList);

    }else if(posted){
      hits="Keine Treffer!";
      model.addAttribute("hits", hits);
      posted=false;
    }


    return"search";
  }

  @Transactional
  @PostMapping("/search")
  public String search(@ModelAttribute SearchString searchString){
     posted = true;
    String substring = searchString.getSubString();
    searchList = searchJobService.findAllJobsByGivenArguments(substring);
    return"redirect:/search";
  }
}
