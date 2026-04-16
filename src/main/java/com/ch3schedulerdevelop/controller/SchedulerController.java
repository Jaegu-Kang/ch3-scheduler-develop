package com.ch3schedulerdevelop.controller;

import com.ch3schedulerdevelop.dto.CreateSchedulerRequest;
import com.ch3schedulerdevelop.dto.CreateSchedulerResponse;
import com.ch3schedulerdevelop.dto.GetAllSchedulerResponse;
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
}
