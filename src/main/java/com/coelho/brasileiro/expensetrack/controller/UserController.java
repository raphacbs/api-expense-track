package com.coelho.brasileiro.expensetrack.controller;


import com.coelho.brasileiro.expensetrack.dto.request.UserRequest;
import com.coelho.brasileiro.expensetrack.service.UserService;
import com.coelho.brasileiro.expensetrack.dto.TokenDto;
import com.coelho.brasileiro.expensetrack.dto.request.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody LoginRequest loginRequest){
       TokenDto tokenDto = this.userService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(tokenDto);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signup(@RequestBody UserRequest userRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.signUp(userRequest));
    }
}
