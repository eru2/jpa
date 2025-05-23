package com.kh.calendar.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) //JPA 스펙상 필수 + 외부 생성 방지
@AllArgsConstructor
@Builder
@DynamicInsert// insert시에 null이 아닌 필드만 쿼리에 포함, default값 활용
@DynamicUpdate// 변경된 필드만 update문에 포함
public class Member {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "USER_ID", length = 30, nullable = false)
    private String userId;

    @Column(name = "USER_PWD", length = 20, nullable = false)
    private String userPwd;

    @Column(name = "USER_NAME", length = 20, nullable = false)
    private String userName;

    @Column(name = "NICK_NAME", length = 30, nullable = false)
    private String nickName;

    @Column(name = "BIRTH", length = 10)
    private LocalDate birth;

    @Column(name = "REASON", length = 200)
    private String reason;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    List<Events> boards = new ArrayList<>();




}
