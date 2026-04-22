package com.ch3schedulerdevelop.comment.repository;

import com.ch3schedulerdevelop.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Long countBySchedulerId(Long schedulerId);
}
