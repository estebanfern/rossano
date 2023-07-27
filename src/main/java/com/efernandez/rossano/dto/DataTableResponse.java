package com.efernandez.rossano.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

@Data
public class DataTableResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer draw;
    private Long recordsTotal;
    private Long recordsFiltered;
    private List<T> data;

    public DataTableResponse(int draw, Page<T> page) {
        this.draw = draw;
        this.recordsTotal = page.getTotalElements();
        this.recordsFiltered = page.getTotalElements();
        this.data = page.getContent();
    }
}
