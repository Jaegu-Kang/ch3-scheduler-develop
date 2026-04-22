package com.ch3schedulerdevelop.scheduler.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class SchedulerListResponse {

    private final List<GetAllSchedulerResponse> schedulerList;


    private SchedulerListResponse(List<GetAllSchedulerResponse> schedulerList) {
        this.schedulerList = schedulerList;
    }

    public static SchedulerListResponse of(List<GetAllSchedulerResponse> schedulerList){
        return new SchedulerListResponse(schedulerList);
    }
}
