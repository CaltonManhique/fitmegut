package com.fitmegut.dciwarehousefinalproject.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exchange")
public class Exchange {

    @GetMapping
    public String exchange(){
        return "exchange-form";
    }
}
