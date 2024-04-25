package com.fitmegut.dciwarehousefinalproject.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/custom-logout")
public class LogoutController {

    private SecurityContextLogoutHandler logoutHandler;


    public LogoutController() {
      logoutHandler = new SecurityContextLogoutHandler();
    }

    @RequestMapping(value = "/signout", method = RequestMethod.POST)
    public String performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("perform-logout");
        this.logoutHandler.logout(request, response, authentication);
        return "redirect:/";
    }


}
