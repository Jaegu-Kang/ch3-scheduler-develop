package com.ch3schedulerdevelop.scheduler.controller;

import com.ch3schedulerdevelop.scheduler.dto.*;
import com.ch3schedulerdevelop.scheduler.dto.*;
import com.ch3schedulerdevelop.scheduler.service.SchedulerService;
import com.ch3schedulerdevelop.user.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/schedulers")
public class SchedulerController {

    private final SchedulerService schedulerService;

    @PostMapping
    public ResponseEntity<CreateSchedulerResponse> saveScheduler(
            @SessionAttribute(name = "LoginUser", required = false) SessionUser sessionUser,
            @RequestBody CreateSchedulerRequest request){

        if (sessionUser == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        CreateSchedulerResponse result = schedulerService.saveScheduler(sessionUser.getId(),request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<SchedulerListResponse> getAllScheduler(){
        SchedulerListResponse result = schedulerService.getAllSchedulerResponse();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{schedulerId}")
    public ResponseEntity<GetAllSchedulerResponse> getOneScheduler(@PathVariable Long schedulerId){
        GetAllSchedulerResponse result = schedulerService.getOneScheduler(schedulerId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping("/{schedulerId}")
    public ResponseEntity<UpdateSchedulerResponse> updateScheduler(
            @PathVariable Long schedulerId,
            @SessionAttribute(name = "LoginUser", required = false) SessionUser sessionUser,
            @RequestBody UpdateSchedulerRequest request){

        if (sessionUser == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UpdateSchedulerResponse result = schedulerService.updateSchedulerResponse(schedulerId,sessionUser.getId(),request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{schedulerId}")
    public ResponseEntity<Void> deleteScheduler(
            @PathVariable Long schedulerId,
            @SessionAttribute(name = "LoginUser", required = false) SessionUser sessionUser)
    {
        if (sessionUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        schedulerService.deleteScheduler(schedulerId,sessionUser.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
