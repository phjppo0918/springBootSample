package com.rptp.rptpSpringBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("hello-rest-api")
    @ResponseBody
    public String helloRestApi(
            @RequestParam("name") String name) {
        return "hello" + name;
    }

    @GetMapping("hello-rest-api-get-class")
    @ResponseBody
    public Hello helloRestApiGetClass(
            @RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);

        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
