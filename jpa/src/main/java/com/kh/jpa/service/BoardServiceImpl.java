package com.kh.jpa.service;

import com.kh.jpa.dto.BoardDto;
import com.kh.jpa.entity.Board;
import com.kh.jpa.entity.BoardTag;
import com.kh.jpa.entity.Member;
import com.kh.jpa.entity.Tag;
import com.kh.jpa.enums.CommonEnums;
import com.kh.jpa.repository.BoardRepository;
import com.kh.jpa.repository.MemberRepository;
import com.kh.jpa.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final TagRepository tagRepository;
    private final String UPLOAD_PATH = "C:\\dev";


    @Override
    public Page<BoardDto.Response> getBoardList(Pageable pageable) {
        /*
            getContent() 실제 데이터 리스트 반환
            getTotalPages() 전체 페이지 개수
            getTotalelements() 전체 데이터 수
            getSize() 페이지당 데이터 수
            ...
         */
        Page<Board> page = boardRepository.findByStatus(CommonEnums.Status.Y, pageable);
        return page.map(BoardDto.Response::toSimpleDto);
    }


    @Override
    public BoardDto.Response getBoardDetail(Long boardNo) {
        Board board = boardRepository.findById(boardNo)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        return BoardDto.Response.toDto(board);
    }

    @Override
    public Long createBoard(BoardDto.Create createBoard) throws IOException {
        //게시글작성
        //작성자 찾기 -> 객체지향코드를 작성할 것이기때문에 key를 직접 외래키로 insert하지않고
        //작성자를 찾아 참조해준다.

        Member member = memberRepository.findOne(createBoard.getUser_id())
                .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다."));

        String changeName = null;
        String originName = null;

        if (createBoard.getFile() != null && !createBoard.getFile()
                .isEmpty()) {
            originName = createBoard.getFile()
                    .getOriginalFilename();
            changeName = UUID.randomUUID()
                    .toString() + "_" + originName;

            File uploadDir = new File(UPLOAD_PATH);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            createBoard.getFile()
                    .transferTo(new File(UPLOAD_PATH + changeName));
        }

        Board board = createBoard.toEntity();
        board.changeMember(member);
        board.changeFile(originName, changeName);

        if (createBoard.getTags() != null && !createBoard.getTags()
                .isEmpty()) {
            //tag가 왔다.
            for (String tagName : createBoard.getTags()) {
                //tag를 이름으로 조회해서 없으면 새로 만들어라.
                Tag tag = tagRepository.findByTagName(tagName)
                        .orElseGet(() -> tagRepository.save(Tag.builder()
                                .tagName(tagName)
                                .build()));

                BoardTag boardTag = BoardTag.builder()
                        .tag(tag)
                        .build();

                boardTag.changeBoard(board);
            }
        }

        return boardRepository.save(board);
    }




    @Override
    public BoardDto.Response updateBoard(Long boardNo, BoardDto.Update updateDto) {
        Board board = boardRepository.findOne(boardNo)
                .orElseThrow(() -> new IllegalArgumentException("존재하지않는 게시글입니다."));
        String changeName = board.getChangeName();
        String originName = board.getOriginName();

        board.updateBoard(
                updateDto.getBoardTitle(),
                updateDto.getBoardContent(),
                updateDto.getOriginName(),
                updateDto.getChangeName(),
                updateDto.getCreateDate()
        );
        return BoardDto.Response.toDto(board);
    }

    @Override
    public void deleteBoard(Long boardNo) {
        Board board = boardRepository.findOne(boardNo)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        boardRepository.delete(board);
    }

    @Override
    public Integer countBoard(Long boardNo) {
        Board board = boardRepository.findById(boardNo)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        board.increaseCount();
        boardRepository.save(board);
        return board.getCount();
    }
}
