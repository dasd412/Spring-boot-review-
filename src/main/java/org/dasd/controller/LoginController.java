package org.dasd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public void login(){


    }

    @GetMapping("/accessDenied")
    public void accessDenied(){

    }

    @GetMapping("/logout")
    public void logout(){

    }
}
