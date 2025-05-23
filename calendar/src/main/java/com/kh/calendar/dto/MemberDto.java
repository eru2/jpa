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
    public static class Create {
        private String user_Id;
        private String user_Pwd;
        private String user_Name;
        private String nick_Name;
        private String birthday;
        private List<String> reason;

        public Member toEntity() {
            return Member.builder()
                    .userId(this.user_Id)
                    .userPwd(this.user_Pwd)
                    .userName(this.user_Name)
                    .nickName(this.nick_Name)
                    .birth(LocalDate.parse(this.birthday))
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
        private String userPwd;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private Integer Id;
        private String user_id;
        private String user_pwd;
        private String user_Name;
        private String nick_Name;
        private LocalDate birthday;
        private List<String> reason;

        public static Response toDto(Member member) {
            return Response.builder()
                    .Id(member.getId())
                    .user_id(member.getUserId())
                    .user_pwd(member.getUserPwd())
                    .user_Name(member.getUserName())
                    .nick_Name(member.getNickName())
                    .birthday(member.getBirth())
                    .reason(member.getReason() != null ? List.of(member.getReason().split(",")) : List.of())
                    .build();
        }
    }
}
