package com.ch3schedulerdevelop.scheduler.entity;

import com.ch3schedulerdevelop.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedulers")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Scheduler extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Scheduler(String title, String content, User user){
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
