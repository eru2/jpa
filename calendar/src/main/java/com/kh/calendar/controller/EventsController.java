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
@RequestMapping("/api/{Id}")
@RequiredArgsConstructor
public class EventsController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventDto.Response>> eventList(@PathVariable("Id") Integer Id, @PageableDefault(size = 5, sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable) {
       return ResponseEntity.ok(eventService.getEventList(Id, pageable));
    }

    @PostMapping
    public ResponseEntity<Integer> addEvent(@RequestBody EventDto.Create createDto) throws IOException {
        return ResponseEntity.ok(eventService.createEvent(createDto));
    }
}
