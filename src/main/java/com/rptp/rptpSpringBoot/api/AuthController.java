package com.rptp.rptpSpringBoot.api;

import com.rptp.rptpSpringBoot.common.security.AuthService;
import com.rptp.rptpSpringBoot.common.security.Token;
import com.rptp.rptpSpringBoot.core.member.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public String login(Model model, @RequestBody LoginRequest loginRequest) {
        model.addAttribute(authService.login(loginRequest));
        return "/login-success";
    }
}
