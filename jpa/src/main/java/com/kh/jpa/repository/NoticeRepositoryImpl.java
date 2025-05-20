package com.kh.jpa.repository;

import com.kh.jpa.entity.Notice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NoticeRepositoryImpl implements NoticeRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void save(Notice notice) {
        em.persist(notice);
    }

    public Optional<Notice> findOne(Long noticeNo) {
        return Optional.ofNullable(em.find(Notice.class, noticeNo));
    }

    @Override
    public void delete(Notice notice) {
        em.remove(notice);
    }

    @Override
    public List<Notice> findByKeyword(String keyword) {
        String query = "SELECT m FROM Notice m WHERE m.noticeTitle = :keyword";
        return em.createQuery(query, Notice.class).setParameter("keyword","%" + keyword + "%").getResultList();
    }
}
