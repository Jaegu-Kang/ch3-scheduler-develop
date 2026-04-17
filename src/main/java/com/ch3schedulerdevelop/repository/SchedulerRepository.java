package com.ch3schedulerdevelop.repository;

import com.ch3schedulerdevelop.entity.Scheduler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulerRepository extends JpaRepository<Scheduler, Long> {
}
