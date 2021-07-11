package com.rptp.rptpSpringBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RptpController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello rptp!!");
        return "hello";
    }

    @GetMapping("hello-get-parameter")
    public String helloGetParameter(
            @RequestParam("name") String name,
            Model model) {
        model.addAttribute("getData", name);
        return "hello-get-parameter";
    }
}
