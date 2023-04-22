package com.disi.travelpoints.controllers.client;

import com.disi.travelpoints.dto.ClientDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("client")
@AllArgsConstructor
public class ClientController {

    @GetMapping("")
    public String test() {
        return "client endpoint";
    }

    @GetMapping("/dto")
    public ClientDto testDto() {
        return ClientDto.builder().firstName("aaaa").build();
    }

}
