package com.kh.jpa.controller;


import com.kh.jpa.dto.NoticeDto;
import com.kh.jpa.entity.Notice;
import com.kh.jpa.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    //공지 작성
    @PostMapping
    public ResponseEntity<String> addNotice(@RequestBody NoticeDto.Create createDto) {
        String userId = noticeService.createNotice(createDto);
        return ResponseEntity.ok(userId);
    }

    @GetMapping("/{noticeNo}")
    public ResponseEntity<NoticeDto.Response> getNotice(@PathVariable Long noticeNo) {
        return ResponseEntity.ok(noticeService.findNotice(noticeNo));
    }

    @PutMapping("/{noticeNo}")
    public ResponseEntity<NoticeDto.Response> updateNotice(@PathVariable Long noticeNo, @RequestBody NoticeDto.Update updateDto) {
        return ResponseEntity.ok(noticeService.updateNotice(noticeNo, updateDto));
    }

    @DeleteMapping("/{noticeNo}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Long noticeNo) {
        noticeService.deleteNotice(noticeNo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search/keyword")
    public ResponseEntity<List<NoticeDto.Response>> searchNoticeByKeyword(@RequestParam String keyword) {
        return ResponseEntity.ok(noticeService.findByKeyword(keyword));
    }
}
