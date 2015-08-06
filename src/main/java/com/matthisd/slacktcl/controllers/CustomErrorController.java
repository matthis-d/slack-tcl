package com.matthisd.slacktcl.controllers;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    public String handleError(){
        return "An error occurred, please try again or later.";
    }

    @Override
    public String getErrorPath() {

        return ERROR_PATH;

    }


}
