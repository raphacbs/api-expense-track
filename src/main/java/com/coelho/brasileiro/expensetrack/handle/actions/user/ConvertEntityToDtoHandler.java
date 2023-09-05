package com.coelho.brasileiro.expensetrack.handle.actions.user;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.dto.UserDTO;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.mapper.UserMapper;
import com.coelho.brasileiro.expensetrack.model.User;
import com.coelho.brasileiro.expensetrack.util.Constants;
import org.springframework.stereotype.Component;

@Component
public class ConvertEntityToDtoHandler extends AbstractHandler {
    @Override
    protected void doHandle(Context context) {
        User user = context.getEntity(Constants.User.USER, User.class);

        UserDTO userDTO = UserMapper.INSTANCE.toDto(user);

        context.setDto(Constants.User.USER_DTO,userDTO);
    }
}
