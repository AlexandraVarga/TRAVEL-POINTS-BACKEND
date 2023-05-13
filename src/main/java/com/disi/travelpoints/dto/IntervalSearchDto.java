package com.disi.travelpoints.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IntervalSearchDto {

    private String filterKey;

    private Long start;

    private Long end;

}
