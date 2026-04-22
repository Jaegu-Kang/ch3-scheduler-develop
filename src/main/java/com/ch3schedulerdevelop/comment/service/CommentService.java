package com.ch3schedulerdevelop.comment.service;

import com.ch3schedulerdevelop.comment.dto.CreateCommentRequest;
import com.ch3schedulerdevelop.comment.dto.CreateCommentResponse;
import com.ch3schedulerdevelop.comment.entity.Comment;
import com.ch3schedulerdevelop.comment.repository.CommentRepository;
import com.ch3schedulerdevelop.exception.SchedulerNotFoundException;
import com.ch3schedulerdevelop.scheduler.entity.Scheduler;
import com.ch3schedulerdevelop.scheduler.repository.SchedulerRepository;
import com.ch3schedulerdevelop.user.dto.CreateUserRequest;
import com.ch3schedulerdevelop.user.entity.User;
import com.ch3schedulerdevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final SchedulerRepository schedulerRepository;

    @Transactional
    public CreateCommentResponse saveComment(Long sessionUserId, CreateCommentRequest request){
        User user = userRepository.findById(sessionUserId).orElseThrow(
                () -> new IllegalArgumentException("로그인 후 작성 가능합니다.")
        );
        Scheduler scheduler = schedulerRepository.findById(request.getSchedulerId()).orElseThrow(
                () -> new SchedulerNotFoundException("없는 일정 입니다.")
        );
        Comment comment = new Comment(
                request.getContent(),
                user,
                scheduler
        );
        Comment savedComment = commentRepository.save(comment);
        return CreateCommentResponse.from(savedComment);
    }
}
