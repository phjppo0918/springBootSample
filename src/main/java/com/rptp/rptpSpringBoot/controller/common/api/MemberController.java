package com.rptp.rptpSpringBoot.controller.common.api;

import com.rptp.rptpSpringBoot.core.member.dto.SignUpRequest;
import com.rptp.rptpSpringBoot.core.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("")
    public String signUp(SignUpRequest req) {
        memberService.signUp(req);
        return "redirect:/sign-up-success";
    }

}
