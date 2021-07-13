package com.rptp.rptpSpringBoot.controller;

import com.rptp.rptpSpringBoot.model.MemberDTO;
import com.rptp.rptpSpringBoot.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createJoinForm() {
        return "members/createJoinForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName(form.getName());

        memberService.join(memberDTO);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<MemberDTO> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
