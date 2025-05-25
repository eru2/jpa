package com.kh.calendar.repository;

import com.kh.calendar.entity.Member;

import java.util.Optional;

public interface MemberRepository {
    void save(Member member);

    Optional<Member> findByUserIdAndUserPwd(String userId, String userPwd);
    Optional<Member> getMembe(Integer id);

    Optional<Member> findByUserId(String userId);

}
