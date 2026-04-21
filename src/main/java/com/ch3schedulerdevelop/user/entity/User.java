package com.ch3schedulerdevelop.user.entity;

import com.ch3schedulerdevelop.scheduler.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    @Size(min = 8)
    private String password;

    public User(String name, String email,String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void update(String name, String email){
        this.name = name;
        this.email = email;
    }
}

