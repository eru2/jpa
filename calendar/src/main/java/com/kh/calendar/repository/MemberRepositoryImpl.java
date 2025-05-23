package com.kh.calendar.repository;

import com.kh.calendar.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Member member) {
        em.persist(member);
    }

    @Override
    public Optional<Member> findByUserIdAndUserPwd(String userId, String userPwd) {
        return em.createQuery(
                "SELECT m FROM Member m WHERE m.userId = :userId AND m.userPwd = :userPwd", Member.class)
                .setParameter("userId", userId)
                .setParameter("userPwd", userPwd)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public Optional<Member> getMembe(Integer id) {
        return Optional.ofNullable(em.find(Member.class, id));
    }
}
