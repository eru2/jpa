package com.kh.calendar.dto;

import com.kh.calendar.entity.Events;
import com.kh.calendar.entity.Member;
import lombok.*;

import java.time.LocalDate;

public class EventDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {
        private String title;
        private String description;
        private LocalDate date;
        private Integer Id;

        public Events toEntity() {
            return Events.builder()
                    .title(title)
                    .description(description)
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
        private String title;
        private String description;
        private LocalDate date;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private Integer event_no;
        private String title;
        private String description;
        private LocalDate date;
        private Integer id;

        public static Response toDto(Events event) {
            return Response.builder()
                    .event_no(event.getEvent_No())
                    .title(event.getTitle())
                    .description(event.getDescription())
                    .date(event.getDate())
                    .id(event.getMember().getId())
                    .build();
        }

    }
}
