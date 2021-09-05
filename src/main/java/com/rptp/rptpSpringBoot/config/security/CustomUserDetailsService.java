package com.rptp.rptpSpringBoot.config.security;

import com.rptp.rptpSpringBoot.common.exceptions.ResourceNotFoundException;
import com.rptp.rptpSpringBoot.core.member.domain.Member;
import com.rptp.rptpSpringBoot.core.member.domain.MemberRepository;
import com.rptp.rptpSpringBoot.core.member.domain.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name)
            throws UsernameNotFoundException {
        Member user = memberRepository.findByName(name)
                .orElseThrow(() ->
                        new UsernameNotFoundException(name + "로 된 사용자를 찾을 수 없습니다.")
                );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        Member user = memberRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.create(user);
    }
}