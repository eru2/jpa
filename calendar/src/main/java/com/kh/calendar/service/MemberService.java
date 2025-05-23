package com.kh.calendar.service;

import com.kh.calendar.dto.MemberDto;
import com.kh.calendar.entity.Member;

import javax.swing.*;

public interface MemberService {
    String createMember(MemberDto.Create createDto);
    Member logInUser(MemberDto.Login loginDto);
    MemberDto.Response getMember(Integer Id);
}
