package com.ch3schedulerdevelop.comment.service;

import com.ch3schedulerdevelop.comment.dto.CommentListResponse;
import com.ch3schedulerdevelop.comment.dto.CreateCommentRequest;
import com.ch3schedulerdevelop.comment.dto.CreateCommentResponse;
import com.ch3schedulerdevelop.comment.dto.GetAllCommentResponse;
import com.ch3schedulerdevelop.comment.entity.Comment;
import com.ch3schedulerdevelop.comment.repository.CommentRepository;
import com.ch3schedulerdevelop.global.exception.SchedulerNotFoundException;
import com.ch3schedulerdevelop.scheduler.entity.Scheduler;
import com.ch3schedulerdevelop.scheduler.repository.SchedulerRepository;
import com.ch3schedulerdevelop.user.entity.User;
import com.ch3schedulerdevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 스프링에 비즈니스 로직을 처리하는 서비스 계층임을 명시합니다.
@Service
// 생성자를 자동으로 만들어줍니다.
@RequiredArgsConstructor
public class CommentService {

    // 댓글을 쓰기위해 유저와 일정의 정보가 필요함으로 각 도메인의 레포지토리를 주입받습니다.
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final SchedulerRepository schedulerRepository;

    // 이 메서드 내부의 모든 작업을 하나의 트랜젝션으로 처리합니다. 중간에 에러가 나면 롤백하여 데이터의 안전성을 보장합니다.
    @Transactional
    public CreateCommentResponse saveComment(Long sessionUserId, CreateCommentRequest request){
        // 유저 검증 및 조회: 세선에서 넘어온 유저id로 확인하여 아니라면 예외처리합니다.
        User user = userRepository.findById(sessionUserId).orElseThrow(
                () -> new IllegalArgumentException("잘못된 접근입니다.")
        );
        // 일정 검증 및 조회: 댓글을 달 일정이 존재하는지 확인합니다.
        Scheduler scheduler = schedulerRepository.findById(request.getSchedulerId()).orElseThrow(
                () -> new SchedulerNotFoundException("없는 일정 입니다.")
        );
        // 엔티티 생성: 검증이 끝난 후 Comment에 넣어줍니다.
        Comment comment = new Comment(
                request.getContent(),
                user,
                scheduler
        );
        // DB 저장: 디비에 인서트 쿼리를 날리고 결과물을 받아옵니다.
        Comment savedComment = commentRepository.save(comment);
        // DTO 변환 및 반환 : 저장된 엔티티를 정적 팩토리 메서드를 사용해 반환합니다.
        return CreateCommentResponse.from(savedComment);
    }
    // 읽기 전용으로 지정합니다.
    @Transactional(readOnly = true)
    public CommentListResponse getAllCommentResponse(){
        // 모든 댓글을 리스트 형태로 가져옵니다.
        List<Comment> comments = commentRepository.findAll();
        // 엔티티리스트를 DTO리스트로 변환후 리스트로 묶어줍니다.
        List<GetAllCommentResponse> list = comments.stream()
                .map(GetAllCommentResponse::from)
                .toList();
        // 변환된 리스트를 리스폰스에 담아 반환합니다.
        return CommentListResponse.of(list);
    }
}
