package com.coelho.brasileiro.expensetrack.handler.actions.user;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.exception.BusinessException;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.model.User;
import com.coelho.brasileiro.expensetrack.repository.UserRepository;
import org.springframework.stereotype.Component;

import static com.coelho.brasileiro.expensetrack.util.BusinessCode.UserCodes.USER_NOT_FOUND;
import static com.coelho.brasileiro.expensetrack.util.Constants.User.USER;

@Component
public class FindUserByEmailHandler extends AbstractHandler {

    private final UserRepository userRepository;

    public FindUserByEmailHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doHandle(Context context) {
        User userToFind = context.getEntity(USER, User.class);
        User userFound = this.userRepository.findByEmail(userToFind.getEmail()).orElseThrow(
                () -> new BusinessException(USER_NOT_FOUND)
        );
        context.setEntity(USER, userFound);
    }
}
