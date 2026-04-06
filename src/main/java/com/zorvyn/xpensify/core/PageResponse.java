package com.zorvyn.xpensify.core;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * @author Sameer Shaikh
 * @date 06-04-2026
 * @description
 */
@Builder
@Getter
public class PageResponse<T> {

    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
