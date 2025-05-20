package com.kh.jpa.service;

import com.kh.jpa.dto.NoticeDto;

import java.util.List;

public interface NoticeService {
    String createNotice(NoticeDto.Create createNotice);
    NoticeDto.Response findNotice(Long noticeNo);
    NoticeDto.Response updateNotice(Long noticeNo,NoticeDto.Update updateNotice);
    void deleteNotice(Long noticeNo);
    List<NoticeDto.Response> findByKeyword(String keyword);
}
