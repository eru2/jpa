package com.kh.jpa.repository;

import com.kh.jpa.entity.Board;
import com.kh.jpa.enums.CommonEnums;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BoardRepositoryImpl implements BoardRepository {

    private EntityManager em;

    @Override
    public Optional<Board> findOne(Long boardNo) {
        return Optional.ofNullable(em.find(Board.class, boardNo));
    }

    @Override
    public Long save(Board board) {
        em.persist(board);
        return board.getBoardNo();
    }


    @Override
    public void delete(Board board) {
        em.remove(board);
    }

    public Page<Board> findByStatus(CommonEnums.Status status, Pageable pageable) {
        //게시글을 pageing처리해서 필요한만큼만 가져오기
        String query = "select b from Board b where b.status=:status";
        List<Board> boards = em.createQuery(query, Board.class)
                .setParameter("status", status)
                .setFirstResult((int) pageable.getOffset()) //어디서부터 가지고 올것인가
                .setMaxResults(pageable.getPageSize()) //몇개를 가지고 올것인가.
                .getResultList();

        String countQuery = "select count(b) from Board b where b.status=:status";
        Long totalCount = em.createQuery(countQuery, Long.class)
                .setParameter("status", status)
                .getSingleResult();

        //Page<T> 인터페이스의 기본구현체를 통해서 pageing한 정보를 한번에 전달할 수 있음
        //new PageImpl<>(content, pageable, total);
        return new PageImpl<Board>(boards, pageable, totalCount);
    }

    @Override
    public Optional<Board> findById(Long boardNo) {
        if(boardNo == null) return Optional.empty();
        return  Optional.ofNullable(em.find(Board.class, boardNo));
    }
}
