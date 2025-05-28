package com.kh.calendar.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.kh.calendar.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MemberDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Create {
        private String userId;
        private String password;
        private String name;
        private String nickName;
        private List<String> reason;

        public Member toEntity() {
            System.out.println("DEBUG: userId = " + this.userId);
            System.out.println("DEBUG: nickName = " + this.nickName);
            return Member.builder()
                    .userId(this.userId)
                    .password(this.password)
                    .userName(this.name)
                    .nickName(this.nickName)
                    .reason(String.join(",", this.reason))
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Login {
        private String userId;
        private String password;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private Integer Id;
        private String userId;
        private String password;
        private String userName;
        private String nickName;
        private List<String> reason;

        public static Response toDto(Member member) {
            return Response.builder()
                    .Id(member.getId())
                    .userId(member.getUserId())
                    .password(member.getPassword())
                    .userName(member.getUserName())
                    .nickName(member.getNickName())
                    .reason(member.getReason() != null ? List.of(member.getReason().split(",")) : List.of())
                    .build();
        }
    }
}
