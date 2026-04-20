package com.ch3schedulerdevelop.scheduler.repository;

import com.ch3schedulerdevelop.scheduler.entity.Scheduler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulerRepository extends JpaRepository<Scheduler, Long> {
}
