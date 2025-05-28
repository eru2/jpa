package com.kh.calendar.controller;

import com.kh.calendar.dto.EventDto;
import com.kh.calendar.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
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
        Long eventNo = eventService.createEvent(createDto);
        return ResponseEntity.ok(eventNo);
    }


    @GetMapping("/{event_no}")
    public ResponseEntity<EventDto.Response> getEvent(@PathVariable Long event_no) {
        EventDto.Response event = eventService.findById(event_no);
        return ResponseEntity.ok(event);
    }

    @PatchMapping("/{event_no}")
    public ResponseEntity<EventDto.Response> updateEvent(
            @PathVariable Long event_no,
            @RequestBody EventDto.Update updateDto) {
        EventDto.Response updated = eventService.updateEvent(event_no, updateDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{event_no}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long event_no) {
        eventService.deleteEvent(event_no);
        return ResponseEntity.ok().build();
    }
}
