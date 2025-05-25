package com.kh.calendar.repository;

import com.kh.calendar.entity.Events;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EventRepository {
    Long save(Events event);
    Optional<Events> findById(Long eventId);
    List<Events> findByUserId(String userId);
    void delete(Events event);
}
