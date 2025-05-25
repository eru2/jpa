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
        private Integer id;
        private String title;
        private String description;
        private LocalDate date;

        public Events toEntity() {
            return Events.builder()
                    .title(title)
                    .description(description)
                    .date(date)
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
        private Long id; // ← 이렇게 수정!
        private String userId;
        private String title;
        private String description;
        private LocalDate date;

        public static Response toDto(Events event) {
            return Response.builder()
                    .title(event.getTitle())
                    .description(event.getDescription())
                    .date(event.getDate())
                    .build();
        }

    }



}
