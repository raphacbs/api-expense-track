package com.coelho.brasileiro.expensetrack.handler.actions.user;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.input.LoginInput;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.model.User;
import org.springframework.stereotype.Component;

import static com.coelho.brasileiro.expensetrack.util.Constants.User.USER;
import static com.coelho.brasileiro.expensetrack.util.Constants.User.USER_INPUT;

@Component
public class ConvertUserInputToUserEntityHandler extends AbstractHandler {
    @Override
    protected void doHandle(Context context) {

        LoginInput loginInputRequest = context.getInput(USER_INPUT, LoginInput.class);

        User user = Converter.INSTANCE.toEntity(loginInputRequest);

        context.setEntity(USER,user);
    }
}
