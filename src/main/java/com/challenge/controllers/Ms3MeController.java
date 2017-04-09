package com.challenge.controllers;

import com.challenge.Model.CustomException;
import com.challenge.services.Multiples;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.TreeMap;

/**
 * Created by ehilario on 4/7/2017.
 */
@Controller

public class Ms3MeController {

    public static final Logger LOGGER = Logger.getLogger(Ms3MeController.class);

    @Autowired
    Multiples multiples;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<?> getMultiples(@RequestParam("start") int start, @RequestParam("end") int end) throws JsonProcessingException, CustomException {
        TreeMap<Integer,String> tm = null;
        String jString = null;
        if(StringUtils.isEmpty(start) || StringUtils.isEmpty(end)){
            throw new CustomException("Parameters empty - Integers Required");
        }
        if(start < 1 || end < 1 || end > 200 || start > 200){
            throw new CustomException("Parameters out side of range");
        }

        jString = multiples.findMultiples(start,end);

        return new ResponseEntity <> (jString, HttpStatus.OK);
    }
}
