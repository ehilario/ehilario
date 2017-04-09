package com.challenge.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by ehilario on 4/7/2017.
 */
@Controller
public class DisplayController {

    @RequestMapping("/")
    String index(){
          return "display";
    }
}
