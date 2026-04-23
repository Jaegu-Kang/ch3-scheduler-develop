package com.ch3schedulerdevelop.global.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
// 클라이언트에게 응답을 줄때 출력형태를 고정합니다.
@JsonPropertyOrder({"results","currentPage","totalPage","totalElements", "isLast"})

// 어떤 객체(일정, 유저, 댓글 등)라도 담을 수 있도록 타입 매개변수를 사용합니다.
public class PageResponse<T> {

    // 외부에서 직접 접근하지 못하도록 private 할당된 값을 바꿀 수 없도록 final로 선언하여 불변성을 보장
    private final List<T> results;
    private final int currentPage;
    private final int totalPage;
    private final long totalElements;
    private final boolean isLast;

    // 외부에서 객체 생성을 막고 내부 로직으로만 객체가 생성되도록 캡슐화합니다.
    private PageResponse(Page<T> page) {
        this.results = page.getContent();
        this.currentPage = page.getNumber();
        this.totalPage = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.isLast = page.isLast();
    }

    // 제네릭 메서드 형식을 갖추어, 입력받은 Page의 타입을 그대로 유지하며 응답 객체를 생성합니다.
    public static <T> PageResponse<T> of(Page<T> page){
        return new PageResponse<>(page);
    }
}
