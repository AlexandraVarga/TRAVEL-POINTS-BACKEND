package com.disi.travelpoints.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TextSearchDto {

    private String filterKey;

    private String value;

}
