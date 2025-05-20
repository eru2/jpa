package com.kh.jpa.dto;

import com.kh.jpa.entity.Member;
import com.kh.jpa.entity.Notice;
import lombok.*;

import java.time.LocalDateTime;

public class NoticeDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {
        private Long noticeNo;
        private String noticeTitle;
        private String noticeContent;
        private Member member;

        public Notice toEntity() {
            return Notice.builder()
                    .noticeNo(this.noticeNo)
                    .noticeTitle(this.noticeTitle)
                    .noticeContent(this.noticeContent)
                    .member(this.member)
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
        private String noticeTitle;
        private String noticeContent;
        private LocalDateTime createDate;
        private Member member;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private Long noticeNo;
        private String noticeTitle;
        private String noticeContent;
        private Member member;
        private LocalDateTime createDate;

        public static Response toDto(Notice notice) {
            return Response.builder()
                    .noticeNo(notice.getNoticeNo())
                    .noticeTitle(notice.getNoticeTitle())
                    .noticeContent(notice.getNoticeContent())
                    .member(notice.getMember())
                    .createDate(notice.getCreateDate())
                    .build();
        }
    }
}

