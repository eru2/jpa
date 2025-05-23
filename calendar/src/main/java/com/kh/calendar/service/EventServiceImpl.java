package com.kh.calendar.service;

import com.kh.calendar.dto.EventDto;
import com.kh.calendar.entity.Events;
import com.kh.calendar.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;


    @Override
    public List<EventDto.Response> getEventList(Integer id, Pageable pageable) {
        return eventRepository.getEventList(id, pageable)
                .map(EventDto.Response::toDto)
                .toList();
    }

    @Override
    public Integer addEvent(EventDto eventDto) {
        return 0;
    }
}
