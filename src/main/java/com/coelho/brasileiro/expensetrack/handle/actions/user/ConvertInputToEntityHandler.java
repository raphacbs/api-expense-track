package com.coelho.brasileiro.expensetrack.handle.actions.user;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.dto.request.LoginRequest;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.mapper.UserMapper;
import com.coelho.brasileiro.expensetrack.model.User;
import org.springframework.stereotype.Component;

import static com.coelho.brasileiro.expensetrack.util.Constants.User.USER_INPUT;
import static com.coelho.brasileiro.expensetrack.util.Constants.User.USER;

@Component
public class ConvertInputToEntityHandler extends AbstractHandler {
    @Override
    protected void doHandle(Context context) {
        LoginRequest loginRequest = context.getInput(USER_INPUT, LoginRequest.class);

        User user = UserMapper.INSTANCE.toEntity(loginRequest);

        context.setEntity(USER,user);
    }
}
