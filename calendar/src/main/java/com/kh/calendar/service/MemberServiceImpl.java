package com.kh.calendar.service;

import com.kh.calendar.dto.MemberDto;
import com.kh.calendar.entity.Member;
import com.kh.calendar.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public String createMember(MemberDto.Create createDto) {
        Member member = createDto.toEntity();
        memberRepository.save(member);
        return member.getNickName();
    }

    @Override
    public MemberDto.Response getMember(Integer Id) {
        return memberRepository.getMembe(Id)
                .map(MemberDto.Response::toDto)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    @Override
    public Member logInUser(MemberDto.Login loginDto) {
        return memberRepository.findByUserIdAndUserPwd(loginDto.getUserId(), loginDto.getUserPwd())
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));
    }

}
