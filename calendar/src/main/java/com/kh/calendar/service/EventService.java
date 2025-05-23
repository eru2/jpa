package com.kh.calendar.service;

import com.kh.calendar.dto.EventDto;
import com.kh.calendar.entity.Events;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<EventDto.Response> getEventList(Integer id, Pageable pageable);
    Integer addEvent(EventDto eventDto);
}
