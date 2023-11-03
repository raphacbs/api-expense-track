package com.coelho.brasileiro.expensetrack.handle.actions.user;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.exception.BusinessException;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.model.User;
import com.coelho.brasileiro.expensetrack.repository.UserRepository;
import org.springframework.stereotype.Component;

import static com.coelho.brasileiro.expensetrack.util.BusinessCode.UserCodes.USER_ALREADY_EXISTS;
import static com.coelho.brasileiro.expensetrack.util.Constants.User.USER;

@Component
public class VerifyIfUserExistHandler extends AbstractHandler {

    private final UserRepository userRepository;

    public VerifyIfUserExistHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doHandle(Context context) {
        User userToFind = context.getEntity(USER, User.class);
        this.userRepository.findByEmail(userToFind.getEmail()).ifPresent(s -> {
                    throw new BusinessException(USER_ALREADY_EXISTS);
                }
        );
    }
}
