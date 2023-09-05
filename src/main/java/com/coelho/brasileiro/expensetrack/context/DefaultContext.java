package com.coelho.brasileiro.expensetrack.context;

import com.coelho.brasileiro.expensetrack.dto.UserDTO;
import com.coelho.brasileiro.expensetrack.model.User;
import com.coelho.brasileiro.expensetrack.util.Constants;
import com.coelho.brasileiro.expensetrack.dto.request.LoginRequest;
import lombok.Builder;

@Builder
public class DefaultContext extends BaseContext{
    public User getUser(){
        return this.getEntity(Constants.User.USER, User.class );
    }

    public void setUser(User user){
        this.setEntity(Constants.User.USER, user);
    }

    public UserDTO getUserDto(){
        return this.getDto(Constants.User.USER_DTO, UserDTO.class );
    }

    public void setUserDto(UserDTO userDTO){
        this.setDto(Constants.User.USER_DTO, userDTO);
    }

    public LoginRequest getUserInput(){
        return this.getInput(Constants.User.USER_INPUT, LoginRequest.class );
    }

    public void setLoginRequest(LoginRequest loginRequest){
        this.setInput(Constants.User.USER_INPUT, loginRequest);
    }
}
