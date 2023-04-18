package com.gaurav.authorizationserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

    @GetMapping("/confirm_logout")
    public String logout()
    {
        return "confirm_logout";
    }

}
