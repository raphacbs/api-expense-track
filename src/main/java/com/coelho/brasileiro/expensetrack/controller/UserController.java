package com.coelho.brasileiro.expensetrack.controller;


import com.coelho.brasileiro.expensetrack.input.LoginInput;
import com.coelho.brasileiro.expensetrack.input.UserInput;
import com.coelho.brasileiro.expensetrack.input.UserUpdate;
import com.coelho.brasileiro.expensetrack.service.UserService;
import com.coelho.brasileiro.expensetrack.dto.TokenDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody LoginInput loginInputRequest){
       TokenDto tokenDto = this.userService.login(loginInputRequest);
        return ResponseEntity.status(HttpStatus.OK).body(tokenDto);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signup(@RequestBody UserInput userInput){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.signUp(userInput));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody UserUpdate userRequest){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.update(id, userRequest));
    }
}
