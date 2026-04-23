package com.ch3schedulerdevelop.comment.repository;

import com.ch3schedulerdevelop.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA가 제공하는 인터페이스를 상속 받고 관리 대상 엔티티를 지정, 해당 엔티티의 기본키 타입을 명시합니다.
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 일정에서 댓글 갯수를 카운팅하는 쿼리 메서드입니다.
    Long countBySchedulerId(Long schedulerId);
}
