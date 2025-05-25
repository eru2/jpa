package com.kh.calendar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) //JPA 스펙상 필수 + 외부 생성 방지
@AllArgsConstructor
@Builder
public class Events {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVENT_NO")
    Long event_No;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private Member member;

    @Column(name = "TITLE", length = 50)
    private String title;

    @Column(length = 500, name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "EVENT_DATE")
    private LocalDate date;

    public void update(String title, String description, LocalDate date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

}
