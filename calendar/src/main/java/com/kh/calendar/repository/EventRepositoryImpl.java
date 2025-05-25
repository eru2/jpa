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
    public Long save(Events event) {
        em.persist(event);
        return event.getEvent_No();
    }

    @Override
    public Optional<Events> findById(Long eventId) {
        return Optional.ofNullable(em.find(Events.class, eventId));
    }

    @Override
    public List<Events> findByUserId(String userId) {
        String query = "SELECT e FROM Events e WHERE e.member.userId = :userId ORDER BY e.date ASC";
        return em.createQuery(query, Events.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public void delete(Events event) {
        em.remove(event);
    }
}
