package com.coelho.brasileiro.expensetrack.handler.actions.user;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.input.UserUpdate;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.model.User;
import org.springframework.stereotype.Component;

import static com.coelho.brasileiro.expensetrack.util.Constants.User.USER;
import static com.coelho.brasileiro.expensetrack.util.Constants.User.USER_INPUT;

@Component
public class ConvertUserUpdateRequestToUserHandler extends AbstractHandler {
    @Override
    protected void doHandle(Context context) {

        UserUpdate userRequest = context.getInput(USER_INPUT, UserUpdate.class);

        User user = Converter.INSTANCE.toEntity(userRequest);

        context.setEntity(USER,user);
    }
}
