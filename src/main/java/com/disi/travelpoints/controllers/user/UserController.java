package com.disi.travelpoints.controllers.user;

import com.disi.travelpoints.dto.UserPasswordDto;
import com.disi.travelpoints.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public String getAllUsers() {
        return "get";
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserPasswordDto> changePassword(@RequestBody UserPasswordDto userPasswordDto, @PathVariable Integer userId) {
        return ResponseEntity.ok(userService.changePassword(userPasswordDto, userId));
    }
}
