package com.disi.travelpoints.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HttpMessageDto {

    private String errorMessage;

    private HttpStatus httpStatus;

}
