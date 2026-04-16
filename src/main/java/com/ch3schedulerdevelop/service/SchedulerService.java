package com.ch3schedulerdevelop.service;

import com.ch3schedulerdevelop.dto.CreateSchedulerRequest;
import com.ch3schedulerdevelop.dto.CreateSchedulerResponse;
import com.ch3schedulerdevelop.entity.Scheduler;
import com.ch3schedulerdevelop.repository.SchedulerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final SchedulerRepository schedulerRepository;

    @Transactional
    public CreateSchedulerResponse saveScheduler(CreateSchedulerRequest request){
        Scheduler scheduler = new Scheduler(
                request.getName(),
                request.getTitle(),
                request.getContent(),
                request.getPassword()
        );
        Scheduler savedScheduler = schedulerRepository.save(scheduler);
        return new CreateSchedulerResponse(
                savedScheduler.getId(),
                savedScheduler.getName(),
                savedScheduler.getTitle(),
                savedScheduler.getContent(),
                savedScheduler.getCreatedAt(),
                savedScheduler.getModifiedAt()
        );
    }
}
