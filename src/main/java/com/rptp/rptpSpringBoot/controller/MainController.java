package com.rptp.rptpSpringBoot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    @GetMapping("")
    public String index(){
        return "/index";
    }

    @GetMapping("sign-up")
    public String signUp() {
        return "/sign-up";
    }

    @GetMapping("sign-up-success")
    public String signUpSuccess() {
        return "/sign-up-success";
    }

    /*

    @GetMapping("login")
    public String login() {
        return "/login";
    }

    @GetMapping("login-success")
    public String loginSuccess() {
        return "/login-success";
    }

    @GetMapping("logout-success")
    public String logoutSuccess() {
        return "/logout-success";
    }

    @GetMapping("denied")
    public String denied() {
        return "/denied";
    }

     */

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

}
