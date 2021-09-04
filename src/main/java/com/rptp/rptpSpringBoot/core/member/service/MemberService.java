package com.rptp.rptpSpringBoot.core.member.service;

import com.rptp.rptpSpringBoot.core.member.domain.Member;
import com.rptp.rptpSpringBoot.core.member.domain.MemberRepository;
import com.rptp.rptpSpringBoot.core.member.domain.Role;
import com.rptp.rptpSpringBoot.core.member.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long signUp(SignUpRequest req) {
        return  memberRepository.save(buildMember(req)).getMemberId();
    }

    private Member buildMember(SignUpRequest req) {
        return Member.builder()
                .name(req.getName())
                .password(passwordEncoder.encode(req.getPassword()))
                .profilePhoto(req.getProfilePhoto())
                .nickName(req.getNickName())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Member member = memberRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException(name + "은 존재하지 않습니다"));

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (member.getRole() == Role.ADMIN) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        }

        authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));

        return new User(member.getName(), member.getPassword(), authorities);
    }

}
