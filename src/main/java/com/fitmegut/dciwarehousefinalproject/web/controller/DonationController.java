package com.fitmegut.dciwarehousefinalproject.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/donation")
public class DonationController {

    @GetMapping
    public String donate(){
        return "donation-form";
    }
}
