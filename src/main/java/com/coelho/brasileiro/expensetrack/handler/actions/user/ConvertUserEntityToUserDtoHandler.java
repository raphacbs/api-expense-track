package com.coelho.brasileiro.expensetrack.handler.actions.user;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.dto.UserDto;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.model.User;
import com.coelho.brasileiro.expensetrack.util.Constants;
import org.springframework.stereotype.Component;

@Component
public class ConvertUserEntityToUserDtoHandler extends AbstractHandler {
    @Override
    protected void doHandle(Context context) {
        User user = context.getEntity(Constants.User.USER, User.class);

        UserDto userDTO = Converter.INSTANCE.toUserDto(user);

        context.setDto(Constants.User.USER_DTO,userDTO);
    }
}
