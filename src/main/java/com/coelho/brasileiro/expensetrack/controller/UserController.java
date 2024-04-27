package com.coelho.brasileiro.expensetrack.controller;


import com.coelho.brasileiro.expensetrack.dto.UserDto;
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
@io.swagger.v3.oas.annotations.tags.Tag(name = "Usuários", description = "Endpoints relacionados à gestão de usuários")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso", content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TokenDto.class))})
    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody LoginInput loginInputRequest){
       TokenDto tokenDto = this.userService.login(loginInputRequest);
        return ResponseEntity.status(HttpStatus.OK).body(tokenDto);
    }

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso", content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UserDto.class))})
    @PostMapping("/sign-up")
    public ResponseEntity<?> signup(@RequestBody UserInput userInput){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.signUp(userInput));
    }

    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
            description = "Usuário atualizado com sucesso",
            content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UserDto.class))})
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody UserUpdate userRequest){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.update(id, userRequest));
    }
}
