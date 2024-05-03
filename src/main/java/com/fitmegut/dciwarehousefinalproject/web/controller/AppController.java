package com.fitmegut.dciwarehousefinalproject.web.controller;

import com.fitmegut.dciwarehousefinalproject.service.interfaces.MemberServiceInterface;
import com.fitmegut.dciwarehousefinalproject.web.dto.MemberRegistrationDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AppController {

    private MemberServiceInterface memberService;

    @Autowired
    public AppController(MemberServiceInterface memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }


    @GetMapping("/home")
    public String home(@ModelAttribute("member") MemberRegistrationDto memberRegistrationDto, Model model) {

        Authentication loggedMember = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedMember.getName();

        memberRegistrationDto = memberService.findByEmail(email);
        model.addAttribute("member", memberRegistrationDto);

        return "home";
    }


}
