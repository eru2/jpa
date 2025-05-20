package com.kh.jpa.service;

import com.kh.jpa.dto.NoticeDto;
import com.kh.jpa.entity.Notice;
import com.kh.jpa.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public String createNotice(NoticeDto.Create createNotice) {
        Notice notice = createNotice.toEntity();
        noticeRepository.save(notice);
        return String.valueOf(notice.getNoticeNo());
    }
    @Transactional(readOnly = true)
    @Override
    public NoticeDto.Response findNotice(Long noticeNo) {
        return noticeRepository.findOne(noticeNo)
                .map(NoticeDto.Response::toDto) //있으면 변환해줘
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 공지입니다."));
    }

    @Override
    public NoticeDto.Response updateNotice(Long noticeNo, NoticeDto.Update updateNotice) {
        Notice notice = noticeRepository.findOne(noticeNo)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 공지입니다."));

        notice.updateNoticeInfo(
                updateNotice.getNoticeTitle(),
                updateNotice.getNoticeContent(),
                updateNotice.getCreateDate(),
                updateNotice.getMember()
                );
        return NoticeDto.Response.toDto(notice);
    }

    @Override
    public void deleteNotice(Long noticeNo) {
        Notice notice = noticeRepository.findOne(noticeNo)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 공지입니다."));
        noticeRepository.delete(notice);
    }

    @Override
    public List<NoticeDto.Response> findByKeyword(String keyword) {
        return noticeRepository.findByKeyword(keyword).stream()
                .map(NoticeDto.Response::toDto)
                .collect(Collectors.toList());
    }
}
