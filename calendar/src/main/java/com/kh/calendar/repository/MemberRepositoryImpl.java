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
    public Optional<Member> findByUserIdAndUserPwd(String user_Id, String password) {
        return em.createQuery(
                "SELECT m FROM Member m WHERE m.userId = :userId AND m.password = :password", Member.class)
                .setParameter("userId", user_Id)
                .setParameter("password", password)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public Optional<Member> getMember(Integer id) {
        return Optional.ofNullable(em.find(Member.class, id));
    }

    @Override
    public Optional<Member> findByUserId(String userId) {
        String query = "SELECT m FROM Member m WHERE m.userId = :userId";
        return em.createQuery(query, Member.class)
                .setParameter("userId", userId)
                .getResultList()
                .stream()
                .findFirst();
    }
    @Override
    public Optional<Member> findById(Integer id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }


}
