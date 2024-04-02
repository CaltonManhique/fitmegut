package com.fitmegut.dciwarehousefinalproject.web.controller;

import com.fitmegut.dciwarehousefinalproject.service.MemberServiceInterface;
import com.fitmegut.dciwarehousefinalproject.web.dto.MemberRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class MemberRegistrationController {

    private MemberServiceInterface memberService;

    public MemberRegistrationController(MemberServiceInterface memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public String showRegistration(Model model){
        model.addAttribute("member", new MemberRegistrationDto());
        return  "registration";
    }

    @PostMapping
    public String registerMemberAccount(@ModelAttribute("member")MemberRegistrationDto registrationDto){
        memberService.save(registrationDto);
        return "redirect:/registration?success";
    }
}
