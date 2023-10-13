package com.coelho.brasileiro.expensetrack.handle.actions.user;

import com.coelho.brasileiro.expensetrack.config.JwtGenerator;
import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.dto.TokenDto;
import com.coelho.brasileiro.expensetrack.dto.UserDto;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.util.Constants;
import org.springframework.stereotype.Component;

@Component
public class GenerateTokenHandler extends AbstractHandler {

    private final JwtGenerator jwtGenerator;

    public GenerateTokenHandler(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @Override
    protected void doHandle(Context context) {
        TokenDto tokenDto = this.jwtGenerator.generateToken(context.getDto(Constants.User.USER_DTO, UserDto.class));
        context.setDto(Constants.Token.TOKEN_DTO, tokenDto);
    }
}
