package com.disi.travelpoints.controllers.client;

import com.disi.travelpoints.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("client")
@AllArgsConstructor
public class ClientController {

    @GetMapping("")
    @PreAuthorize("hasRole('CLIENT')")
    public String test() {
        return "client endpoint";
    }

}
