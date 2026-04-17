package com.ch3schedulerdevelop.service;

import com.ch3schedulerdevelop.dto.*;
import com.ch3schedulerdevelop.entity.Scheduler;
import com.ch3schedulerdevelop.repository.SchedulerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Transactional(readOnly = true)
    public List<GetAllSchedulerResponse> getAllSchedulerResponse(){
        List<Scheduler> schedulers = schedulerRepository.findAll();
        List<GetAllSchedulerResponse> dtos = new ArrayList<>();

        for (Scheduler scheduler : schedulers){
            GetAllSchedulerResponse dto = new GetAllSchedulerResponse(
                    scheduler.getId(),
                    scheduler.getName(),
                    scheduler.getTitle(),
                    scheduler.getContent(),
                    scheduler.getCreatedAt(),
                    scheduler.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public GetAllSchedulerResponse getOneScheduler(Long userId){
        Scheduler scheduler = schedulerRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        return new GetAllSchedulerResponse(
                scheduler.getId(),
                scheduler.getName(),
                scheduler.getTitle(),
                scheduler.getContent(),
                scheduler.getCreatedAt(),
                scheduler.getModifiedAt()
        );
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

        return new UpdateSchedulerResponse(
                scheduler.getId(),
                scheduler.getName(),
                scheduler.getTitle(),
                scheduler.getContent(),
                scheduler.getCreatedAt(),
                scheduler.getModifiedAt()
        );
    }

    @Transactional
    public void deleteScheduler(Long userId){
        Scheduler scheduler = schedulerRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );
        schedulerRepository.delete(scheduler);
    }
}
