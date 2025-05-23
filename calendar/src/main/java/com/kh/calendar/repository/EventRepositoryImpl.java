package com.kh.calendar.repository;

import com.kh.calendar.entity.Events;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jdk.jfr.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EventRepositoryImpl implements EventRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<Events> getEventList(Integer id, Pageable pageable) {
        List<Events> events = em.createQuery(
                "SELECT e FROM Events e WHERE e.member.userId = :userId ORDER BY e.date ASC", Events.class)
                .setParameter("userId", id)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        Long total = em.createQuery(
                "SELECT COUNT(e) FROM Events e WHERE e.member.userId = :userId", Long.class)
                .setParameter("userId", id)
                .getSingleResult();

        return new PageImpl<Events>(events, pageable, total);
    }
}
