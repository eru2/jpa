package com.kh.calendar.repository;

import com.kh.calendar.entity.Events;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EventRepository {
    Page<Events> getEventList(Integer id, Pageable pageable);
}
