package com.ch3schedulerdevelop.controller;

import com.ch3schedulerdevelop.dto.*;
import com.ch3schedulerdevelop.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedulers")
public class SchedulerController {

    private final SchedulerService schedulerService;

    @PostMapping
    public ResponseEntity<CreateSchedulerResponse> saveScheduler(@RequestBody CreateSchedulerRequest request){
        CreateSchedulerResponse result = schedulerService.saveScheduler(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<List<GetAllSchedulerResponse>> getAllScheduler(){
        List<GetAllSchedulerResponse> result = schedulerService.getAllSchedulerResponse();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GetAllSchedulerResponse> getOneScheduler(@PathVariable Long userId){
        GetAllSchedulerResponse result = schedulerService.getOneScheduler(userId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UpdateSchedulerResponse> updateScheduler(@PathVariable Long userId, @RequestBody UpdateSchedulerRequest request){
        UpdateSchedulerResponse result = schedulerService.updateSchedulerResponse(userId, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
