package com.busanit501.lhslunchproject.repository;

import com.busanit501.lhslunchproject.domain.LunchReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LunchReplyRepository extends JpaRepository<LunchReply, Long> {
    @Query("select r from LunchReply r where r.lunch.bno = :bno")
    Page<LunchReply> listOfBoard(Long mno, Pageable pageable);
}
