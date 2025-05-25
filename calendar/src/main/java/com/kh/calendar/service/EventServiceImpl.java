package com.kh.calendar.service;

import com.kh.calendar.dto.EventDto;
import com.kh.calendar.entity.Events;
import com.kh.calendar.entity.Member;
import com.kh.calendar.repository.EventRepository;
import com.kh.calendar.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Long createEvent(EventDto.Create createDto) {

        Member member = memberRepository.findById(createDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        Events event = createDto.toEntity();
        event.setMember(member);
        eventRepository.save(event);

        return event.getEvent_No();
    }


    @Override
    @Transactional
    public List<EventDto.Response> findByUserId(String userId) {
        List<Events> events = eventRepository.findByUserId(userId);
        return events.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EventDto.Response findById(Long eventId) {
        Events event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("일정을 찾을 수 없습니다."));
        return toDto(event);
    }

    @Override
    public EventDto.Response updateEvent(Long eventId, EventDto.Update updateDto) {
        Events event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("일정을 찾을 수 없습니다."));

        event.update(updateDto.getTitle(), updateDto.getDescription(), updateDto.getDate());
        return toDto(event);
    }

    @Override
    @Transactional
    public void deleteEvent(Long eventId) {
        Events event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("일정을 찾을 수 없습니다."));
        eventRepository.delete(event);
    }

    private EventDto.Response toDto(Events event) {
        return EventDto.Response.builder()
                .id(event.getEvent_No())
                .userId(event.getMember().getUserId())
                .title(event.getTitle())
                .description(event.getDescription())
                .date(event.getDate())
                .build();
    }
}