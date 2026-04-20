package com.ch3schedulerdevelop.user.repository;

import com.ch3schedulerdevelop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
