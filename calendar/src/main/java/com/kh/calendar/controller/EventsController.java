package com.kh.calendar.controller;

import com.kh.calendar.dto.EventDto;
import com.kh.calendar.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventsController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventDto.Response>> getEvents(@RequestParam("userId") String userId) {
        List<EventDto.Response> events = eventService.findByUserId(userId);
        return ResponseEntity.ok(events);
    }

    @PostMapping
    public ResponseEntity<Long> createEvent(@RequestBody EventDto.Create createDto) {
        Long id = eventService.createEvent(createDto);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto.Response> getEvent(@PathVariable Long id) {
        EventDto.Response event = eventService.findById(id);
        return ResponseEntity.ok(event);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EventDto.Response> updateEvent(
            @PathVariable Long id,
            @RequestBody EventDto.Update updateDto) {
        EventDto.Response updated = eventService.updateEvent(id, updateDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }
}
