package com.ch3schedulerdevelop.scheduler.controller;

import com.ch3schedulerdevelop.global.dto.PageResponse;
import com.ch3schedulerdevelop.scheduler.dto.*;
import com.ch3schedulerdevelop.scheduler.dto.*;
import com.ch3schedulerdevelop.scheduler.service.SchedulerService;
import com.ch3schedulerdevelop.user.dto.SessionUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequiredArgsConstructor
@RequestMapping("api/schedulers")
public class SchedulerController {

    private final SchedulerService schedulerService;

    // 일정 생성 새로운 일정을 저장하며 세션체크를 통해 로그인 여부를 확인합니다.
    @PostMapping
    public ResponseEntity<CreateSchedulerResponse> saveScheduler(
            @SessionAttribute(name = "LoginUser", required = false) SessionUser sessionUser,
            @Valid @RequestBody CreateSchedulerRequest request){

        // 로그인 정보가 없으면 401(Unauthorized) 상태를 반환합니다.
        if (sessionUser == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // 서비스에서 저장된 결과를 받아 201(Created) 코드로 응답합니다
        CreateSchedulerResponse result = schedulerService.saveScheduler(sessionUser.getId(),request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // 전체 조회 등록된 모든 일정을 리스트 형태로 조회합니다.
    @GetMapping
    public ResponseEntity<SchedulerListResponse> getAllScheduler(){
        SchedulerListResponse result = schedulerService.getAllSchedulerResponse();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 단건 조회 경로 변수({schedulerId})를 사용하여 특정 일정 하나를 상세 조회합니다.
    @GetMapping("/{schedulerId}")
    public ResponseEntity<GetAllSchedulerResponse> getOneScheduler(@PathVariable Long schedulerId){
        GetAllSchedulerResponse result = schedulerService.getOneScheduler(schedulerId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 페이징 조회 @PageableDefault를 사용하여 기본 페이지 설정(10개씩, 수정일 기준 내림차순)을 적용한 페이징 조회를 수행합니다.
    @GetMapping("/page")
    public ResponseEntity<PageResponse<SchedulePageResponse>> getSchedulerPage(
            @PageableDefault(size = 10, sort = "modifiedAt", direction = Sort.Direction.DESC) Pageable pageable){
        Page<SchedulePageResponse> result = schedulerService.getSchedulePages(pageable);
        // PageResponse.of()를 통해 규격화된 페이징 데이터를 반환합니다.
        return ResponseEntity.status(HttpStatus.OK).body(PageResponse.of(result));
    }

    // 일정 수정 기존 일정 정보를 수정하며, 작성자 본인 확인은 서비스 계층에서 처리합니다.
    @PatchMapping("/{schedulerId}")
    public ResponseEntity<UpdateSchedulerResponse> updateScheduler(
            @PathVariable Long schedulerId,
            @SessionAttribute(name = "LoginUser", required = false) SessionUser sessionUser,
            @Valid @RequestBody UpdateSchedulerRequest request)
            {
                // 로그인 정보가 없으면 401(Unauthorized) 상태를 반환합니다.
        if (sessionUser == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // 서비스에서 저장된 결과를 받아 200(OK) 코드로 응답합니다
        UpdateSchedulerResponse result = schedulerService.updateSchedulerResponse(schedulerId,sessionUser.getId(),request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 일정 삭제
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
