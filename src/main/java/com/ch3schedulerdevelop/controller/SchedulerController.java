package com.ch3schedulerdevelop.controller;

import com.ch3schedulerdevelop.dto.CreateSchedulerRequest;
import com.ch3schedulerdevelop.dto.CreateSchedulerResponse;
import com.ch3schedulerdevelop.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
