package com.kh.calendar.dto;

import com.kh.calendar.entity.Events;
import com.kh.calendar.entity.Member;
import lombok.*;

import java.time.LocalDate;

public class EventDto {
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {
        private String user_Id;
        private String title;
        private String description;
        private LocalDate date;

        public Events toEntity(Member member) {
            return Events.builder()
                    .member(member)
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
        private Long event_No;
        private Integer id;
        private String title;
        private String description;
        private LocalDate date;

        public static Response toDto(Events event) {
            return Response.builder()
                    .event_No(event.getEvent_No()) // ← 누락되어 있던 부분
                    .id(event.getMember().getId()) // ← 없으면 추가
                    .title(event.getTitle())
                    .description(event.getDescription())
                    .date(event.getDate())
                    .build();
        }

    }



}
