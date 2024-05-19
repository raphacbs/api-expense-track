package com.coelho.brasileiro.expensetrack.handler.actions.user;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.input.UserUpdate;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.model.User;
import com.coelho.brasileiro.expensetrack.util.Constants;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserEntityHandler extends AbstractHandler {
    @Override
    protected void doHandle(Context context) {
        User user = context.getEntity(Constants.User.USER, User.class);
        UserUpdate userUpdateRequest = context.getInput(Constants.User.USER_INPUT, UserUpdate.class);

        User userUpdated = Converter.INSTANCE.partialUpdate(userUpdateRequest, user);

        context.setEntity(Constants.User.USER,userUpdated);
    }
}
