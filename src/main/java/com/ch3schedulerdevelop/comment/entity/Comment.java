package com.ch3schedulerdevelop.comment.entity;

import com.ch3schedulerdevelop.scheduler.entity.BaseEntity;
import com.ch3schedulerdevelop.scheduler.entity.Scheduler;
import com.ch3schedulerdevelop.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

//JPA가 관리하는 객체임을 선언합니다, 애플리케이션이 실행될 때 이 클래스를 바탕으로 DB에 테이블이 생성되거나 매핑됩니다.
@Entity
// 내부 값을 외부에서 읽어 갈수있도록 게터 메서드를 자동으로 생성
@Getter
//DB에 생성될 실제 테이블 이름을 "comments"로 지정합니다.
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 공통 속성인 생성일과 수정일을 상속받습니다.
public class Comment extends BaseEntity {
    //기본키 임을 JPA에 알려줍니다.
    @Id
    // 기본 키 생성을 위임하여 순차적으로 증가하게 합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 컬럼에 내용이 비어있을 수 없도록 제한합니다.
    @Column(nullable = false)
    private String content;
    // 유저와의 다대일 관계를 설정합니다, 지연 로딩하여 쿼리 성능을 최적화합니다.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    // 외래키 컬럼명을 지정합니다, 공백 제한
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    // 일정과 다대일 관계를 설정하고, 지연 로딩하여 쿼리 성능을 최적화합니다.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    // 외래키 컬럼명을 지정합니다.
    @JoinColumn(name = "scheduler_id", nullable = false)
    private Scheduler scheduler;
    // 서비스에서 댓글 엔티티를 생성할때 필요한 내용만 받아 객체를 조립합니다.
    public Comment(String content, User user, Scheduler scheduler){
        this.content = content;
        this.user = user;
        this.scheduler = scheduler;
    }

}
