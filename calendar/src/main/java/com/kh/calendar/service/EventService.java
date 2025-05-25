package com.kh.calendar.service;

import com.kh.calendar.dto.EventDto;
import com.kh.calendar.entity.Events;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EventService {
    Long createEvent(EventDto.Create createDto);

    List<EventDto.Response> findByUserId(String userId);

    EventDto.Response findById(Long eventId);

    EventDto.Response updateEvent(Long eventId, EventDto.Update updateDto);

    void deleteEvent(Long eventId);
}
