package com.ch3schedulerdevelop.global.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@JsonPropertyOrder({"results","currentPage","totalPage","totalElements", "isLast"})
public class PageResponse<T> {

    private final List<T> results;
    private final int currentPage;
    private final int totalPage;
    private final long totalElements;
    private final boolean isLast;

    private PageResponse(Page<T> page) {
        this.results = page.getContent();
        this.currentPage = page.getNumber();
        this.totalPage = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.isLast = page.isLast();
    }

    public static <T> PageResponse<T> of(Page<T> page){
        return new PageResponse<>(page);
    }
}
