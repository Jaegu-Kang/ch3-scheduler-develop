package com.ch3schedulerdevelop.scheduler.service;

import com.ch3schedulerdevelop.scheduler.dto.*;
import com.ch3schedulerdevelop.scheduler.entity.Scheduler;
import com.ch3schedulerdevelop.scheduler.repository.SchedulerRepository;
import com.ch3schedulerdevelop.scheduler.dto.*;
import com.ch3schedulerdevelop.user.entity.User;
import com.ch3schedulerdevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final SchedulerRepository schedulerRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateSchedulerResponse saveScheduler(CreateSchedulerRequest request){
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("없는 유저입니다.")
        );
        Scheduler scheduler = new Scheduler(
                request.getTitle(),
                request.getContent(),
                user
        );
        Scheduler savedScheduler = schedulerRepository.save(scheduler);
        return CreateSchedulerResponse.from(savedScheduler);
    }

    @Transactional(readOnly = true)
    public SchedulerListResponse getAllSchedulerResponse() {
        List<Scheduler> schedulers = schedulerRepository.findAll();

       List<GetAllSchedulerResponse> list = schedulers.stream()
                .map(GetAllSchedulerResponse::from)
                .toList();
        return SchedulerListResponse.of(list);
    }

    @Transactional(readOnly = true)
    public GetAllSchedulerResponse getOneScheduler(Long schedulerId){
        Scheduler scheduler = schedulerRepository.findById(schedulerId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        return GetAllSchedulerResponse.from(scheduler);

    }

    @Transactional
    public UpdateSchedulerResponse updateSchedulerResponse(Long schedulerId, Long sessionUserId ,UpdateSchedulerRequest request){
        Scheduler scheduler = schedulerRepository.findById(schedulerId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        if (!scheduler.getUser().getId().equals(sessionUserId)){
            throw new IllegalStateException("본인의 일정만 수정할 수 있습니다.");
        }
        scheduler.update(
                request.getTitle(),
                request.getContent()
        );

        return UpdateSchedulerResponse.from(scheduler);
    }

    @Transactional
    public void deleteScheduler(Long schedulerId){
        Scheduler scheduler = schedulerRepository.findById(schedulerId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );
        schedulerRepository.delete(scheduler);
    }
}
