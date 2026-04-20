package com.ch3schedulerdevelop.scheduler.service;

import com.ch3schedulerdevelop.scheduler.dto.*;
import com.ch3schedulerdevelop.scheduler.entity.Scheduler;
import com.ch3schedulerdevelop.scheduler.repository.SchedulerRepository;
import com.ch3schedulerdevelop.scheduler.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final SchedulerRepository schedulerRepository;

    @Transactional
    public CreateSchedulerResponse saveScheduler(CreateSchedulerRequest request){
        Scheduler scheduler = new Scheduler(
                request.getName(),
                request.getTitle(),
                request.getContent()
        );
        Scheduler savedScheduler = schedulerRepository.save(scheduler);
        return CreateSchedulerResponse.from(savedScheduler);
    }

    @Transactional(readOnly = true)
    public List<GetAllSchedulerResponse> getAllSchedulerResponse() {
        List<Scheduler> schedulers = schedulerRepository.findAll();

        return schedulers.stream()
                .map(GetAllSchedulerResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public GetAllSchedulerResponse getOneScheduler(Long userId){
        Scheduler scheduler = schedulerRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        return GetAllSchedulerResponse.from(scheduler);

    }

    @Transactional
    public UpdateSchedulerResponse updateSchedulerResponse(Long userId, UpdateSchedulerRequest request){
        Scheduler scheduler = schedulerRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        scheduler.update(
                request.getName(),
                request.getTitle(),
                request.getContent()
        );

        return UpdateSchedulerResponse.from(scheduler);
    }

    @Transactional
    public void deleteScheduler(Long userId){
        Scheduler scheduler = schedulerRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );
        schedulerRepository.delete(scheduler);
    }
}
