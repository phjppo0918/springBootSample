package com.rptp.rptpSpringBoot.api;

import com.rptp.rptpSpringBoot.config.security.AuthService;
import com.rptp.rptpSpringBoot.core.member.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/login")
    public String loginPage(){
        return "/auth/login";
    }

    @PostMapping("/login")
    public String login(Model model, LoginRequest loginRequest) {
        model.addAttribute(authService.login(loginRequest));
        return "/login-success";
    }
}
