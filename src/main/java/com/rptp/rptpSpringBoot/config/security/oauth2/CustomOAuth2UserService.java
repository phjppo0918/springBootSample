package com.rptp.rptpSpringBoot.config.security.oauth2;

import com.rptp.rptpSpringBoot.common.exceptions.OAuth2AuthenticationProcessingException;
import com.rptp.rptpSpringBoot.config.security.oauth2.user.OAuth2UserInfo;
import com.rptp.rptpSpringBoot.config.security.oauth2.user.OAuth2UserInfoFactory;
import com.rptp.rptpSpringBoot.core.member.domain.AuthProvider;
import com.rptp.rptpSpringBoot.core.member.domain.Member;
import com.rptp.rptpSpringBoot.core.member.domain.MemberRepository;
import com.rptp.rptpSpringBoot.core.member.domain.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        }catch (AuthenticationException ex) {
            throw ex;
        }catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    //사용자 정보 추출
    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo =
                OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(),
                       oAuth2User.getAttributes());
        if(!StringUtils.hasText(oAuth2UserInfo.getName())) {
            throw new OAuth2AuthenticationProcessingException("OAuth2 공급자(구글,네이버... ) 에서 계정을 찾을 수 없습니다.");
        }

        Optional<Member> userOptional = memberRepository.findByName(oAuth2UserInfo.getName());
        Member member;
        if(userOptional.isPresent()) {
            member = userOptional.get();
            if(!member.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                throw new OAuth2AuthenticationProcessingException(
                        member.getProvider() + "계정을 사용하기 위해서 로그인을 해야합니다.");
            }
            member = updateExistingMember(member, oAuth2UserInfo);
        } else {
            member = registerNewMember(oAuth2UserRequest, oAuth2UserInfo);
        }
        return UserPrincipal.create(member, oAuth2User.getAttributes());
    }

    //DB에 존재하지 않을 경우 새로 등록
    private Member registerNewMember (OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        return memberRepository.save(
                Member.builder()
                        .name(oAuth2UserInfo.getName())
                        .email(oAuth2UserInfo.getEmail())
                        .imageUrl(oAuth2UserInfo.getImageUrl())
                        .provider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))
                        .providerId(oAuth2UserInfo.getId())
                        .build()
        );
    }

    //DB에 존재할 경우 정보 업데이트
    private Member updateExistingMember(Member existingMember, OAuth2UserInfo oAuth2MemberInfo) {
        return memberRepository.save(
                existingMember.update(
                        oAuth2MemberInfo.getName(),
                        oAuth2MemberInfo.getImageUrl()
                )
        );
    }
}
