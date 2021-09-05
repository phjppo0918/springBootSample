package com.rptp.rptpSpringBoot.api;

import com.rptp.rptpSpringBoot.config.security.CurrentUser;
import com.rptp.rptpSpringBoot.core.member.domain.Member;
import com.rptp.rptpSpringBoot.core.member.domain.UserPrincipal;
import com.rptp.rptpSpringBoot.core.member.dto.SignUpRequest;
import com.rptp.rptpSpringBoot.core.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/auth")
    public Member getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return memberService.findByMemberId(userPrincipal.getId());
    }


}
