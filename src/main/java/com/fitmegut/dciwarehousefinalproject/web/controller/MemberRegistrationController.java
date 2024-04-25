package com.fitmegut.dciwarehousefinalproject.web.controller;

import com.fitmegut.dciwarehousefinalproject.service.interfaces.MemberServiceInterface;
import com.fitmegut.dciwarehousefinalproject.web.dto.MemberRegistrationDto;
import com.fitmegut.dciwarehousefinalproject.web.dto.PasswordRecoverDto;
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
    public String verifyMember(@Param("code") String code) {
        if (memberService.verify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }

    @GetMapping("/new-password")
    public String showNewPassword(Model model) {
        model.addAttribute("newPassword", new PasswordRecoverDto());
        return "new-password";
    }

    @PostMapping("/passwordRecover")
    public String passwordRecover(@Valid @ModelAttribute("newPassword") PasswordRecoverDto passwordRecoverDto,
                                  HttpServletRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "new-password";
        }else {
            memberService.sendPasswordResetEmail(passwordRecoverDto.getEmail(), getSiteURL(request));

            return "redirect:/index";
        }
    }


    @GetMapping("/password-recovering")
    public String passwordRecovering(@Param("code") String code, Model model) {
        if (memberService.verify(code)) {
            return "password-recovering";
        } else {
            return "login";
        }
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
