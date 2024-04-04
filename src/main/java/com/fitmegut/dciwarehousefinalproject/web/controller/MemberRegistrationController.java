package com.fitmegut.dciwarehousefinalproject.web.controller;

import com.fitmegut.dciwarehousefinalproject.service.MemberServiceInterface;
import com.fitmegut.dciwarehousefinalproject.web.dto.MemberRegistrationDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class MemberRegistrationController {

    private MemberServiceInterface memberService;

    @Autowired
    public MemberRegistrationController(MemberServiceInterface memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public String showRegistration(Model model) {
        model.addAttribute("member", new MemberRegistrationDto());
        return "registration";
    }

    @PostMapping
    public @ResponseBody String registerMemberAccount(@Valid @ModelAttribute("member") MemberRegistrationDto registrationDto,
                                                      HttpServletRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        } else {
            memberService.save(registrationDto, getSiteURL(request));
            return "redirect:/registration?success";
        }
    }

    @GetMapping("/verify")
    public String verifyMember(@Param("code") String code){
        if(memberService.verify(code)){
            return "verify_success";
        }else{
            return "verify_fail";
        }
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
