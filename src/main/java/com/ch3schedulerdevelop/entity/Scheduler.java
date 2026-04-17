package com.ch3schedulerdevelop.entity;

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
    private String name;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String password;

    public Scheduler(String name, String title, String content, String password){
        this.name = name;
        this.title = title;
        this.content = content;
        this.password = password;
    }

    public void update(String name, String title, String content){
        this.name = name;
        this.title = title;
        this.content = content;
    }

}
