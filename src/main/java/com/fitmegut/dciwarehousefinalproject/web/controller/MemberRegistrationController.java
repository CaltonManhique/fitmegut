package com.fitmegut.dciwarehousefinalproject.web.controller;

import com.fitmegut.dciwarehousefinalproject.service.interfaces.MemberServiceInterface;
import com.fitmegut.dciwarehousefinalproject.web.dto.*;
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
        return "member/registration";
    }

    @PostMapping
    public @ResponseBody String registerMemberAccount(@Valid @ModelAttribute("member") MemberRegistrationDto registrationDto,
                                                      HttpServletRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        } else {
            memberService.save(registrationDto, getSiteURL(request));
            return "member/verify_email";
        }
    }

    @GetMapping("/verify")
    public String verifyMember(@Param("code") String code) {
        if (memberService.verify(code, 1)) {
            return "member/verify_success";
        } else {
            return "member/verify_fail";
        }
    }

    @GetMapping("/new-password")
    public String showNewPassword(Model model) {
        model.addAttribute("newPassword", new SendEmailDto());
        return "member/new-password";
    }

    @PostMapping("/passwordRecover")
    public String passwordRecover(@Valid @ModelAttribute("newPassword") SendEmailDto emailDto,
                                  HttpServletRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "member/new-password";
        } else {
            memberService.sendPasswordResetEmail(emailDto.getEmail(), getSiteURL(request));

            return "member/verify_email";
        }
    }

    @GetMapping("/passwordRecovering")
    public String passwordRecovering(@Param("code") String code, Model model) {
        if (memberService.verify(code, 2)) {
            model.addAttribute("newPassword", new PasswordRecoverDto());
            return "member/password-recovering";
        } else {
            return "member/verify_fail";
        }
    }

    @PostMapping("/processNewPwd")
    public String processNewPassword(@Valid @ModelAttribute("newPassword") PasswordRecoverDto passwordRecoverDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || !passwordRecoverDto.getNewPassword().equals(passwordRecoverDto.getRepeatPassword())) {
            return "member/password-recovering";
        }

        MemberRegistrationDto registrationDto = memberService.findByEmail(passwordRecoverDto.getEmail());
        registrationDto.setPassword(passwordRecoverDto.getNewPassword());
        memberService.save(registrationDto);

        return "redirect:/login";
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
