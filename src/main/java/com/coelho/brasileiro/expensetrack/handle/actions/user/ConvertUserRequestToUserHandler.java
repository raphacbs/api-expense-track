package com.coelho.brasileiro.expensetrack.handle.actions.user;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.dto.request.LoginRequest;
import com.coelho.brasileiro.expensetrack.dto.request.UserRequest;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.mapper.UserMapper;
import com.coelho.brasileiro.expensetrack.model.User;
import org.springframework.stereotype.Component;

import static com.coelho.brasileiro.expensetrack.util.Constants.User.USER;
import static com.coelho.brasileiro.expensetrack.util.Constants.User.USER_INPUT;

@Component
public class ConvertUserRequestToUserHandler extends AbstractHandler {
    @Override
    protected void doHandle(Context context) {

        UserRequest userRequest = context.getInput(USER_INPUT, UserRequest.class);

        User user = UserMapper.INSTANCE.toEntity(userRequest);

        context.setEntity(USER,user);
    }
}
