package com.coelho.brasileiro.expensetrack.handler.actions.user;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.input.UserInput;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.model.User;
import org.springframework.stereotype.Component;

import static com.coelho.brasileiro.expensetrack.util.Constants.User.USER;
import static com.coelho.brasileiro.expensetrack.util.Constants.User.USER_INPUT;

@Component
public class ConvertUserInputToUserHandler extends AbstractHandler {
    @Override
    protected void doHandle(Context context) {

        UserInput userInput = context.getInput(USER_INPUT, UserInput.class);

        User user = Converter.INSTANCE.toEntity(userInput);

        context.setEntity(USER,user);
    }
}
