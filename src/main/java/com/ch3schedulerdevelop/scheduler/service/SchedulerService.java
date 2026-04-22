package com.ch3schedulerdevelop.scheduler.service;

import com.ch3schedulerdevelop.exception.SchedulerNotFoundException;
import com.ch3schedulerdevelop.exception.UnauthorizedAccessException;
import com.ch3schedulerdevelop.scheduler.dto.*;
import com.ch3schedulerdevelop.scheduler.entity.Scheduler;
import com.ch3schedulerdevelop.scheduler.repository.SchedulerRepository;
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
    public CreateSchedulerResponse saveScheduler(Long sessionUserId, CreateSchedulerRequest request){
        User user = userRepository.findById(sessionUserId).orElseThrow(
                () -> new IllegalArgumentException("로그인 후 작성 가능합니다.")
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
                () -> new SchedulerNotFoundException("존재하지 않는 일정입니다.")
        );
        return GetAllSchedulerResponse.from(scheduler);

    }

    @Transactional
    public UpdateSchedulerResponse updateSchedulerResponse(Long schedulerId, Long sessionUserId ,UpdateSchedulerRequest request)
        {
        Scheduler scheduler = schedulerRepository.findById(schedulerId).orElseThrow(
                () -> new SchedulerNotFoundException("존재하지 않는 일정입니다.")
        );
        if (!scheduler.getUser().getId().equals(sessionUserId)){
            throw new UnauthorizedAccessException("권한이 없습니다.");
        }
        scheduler.update(
                request.getTitle(),
                request.getContent()
        );

        return UpdateSchedulerResponse.from(scheduler);
    }

    @Transactional
    public void deleteScheduler(Long schedulerId, Long sessionUserId){
        Scheduler scheduler = schedulerRepository.findById(schedulerId).orElseThrow(
                () -> new SchedulerNotFoundException("존재하지 않는 일정입니다.")
        );
        if (!scheduler.getUser().getId().equals(sessionUserId)){
            throw new UnauthorizedAccessException("권한이 없습니다.");
        }
        schedulerRepository.delete(scheduler);
    }
}
