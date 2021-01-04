package com.headly.Headly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LawController {

    @GetMapping("/datenschutz")
    public String datenschutzerklaerung(){
        return "datenschutz";
    }

    @GetMapping("/impressum")
    public String impressum(){
        return "Impressum";
    }

    @GetMapping("/agb")
    public String agb(){
        return"agb";
    }

}
