package com.example.study.model.network;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class Pagination {

    private Integer totalPages;
    private Long totalElements;
    private Integer currentPage;
    private Integer currentElements;

}
