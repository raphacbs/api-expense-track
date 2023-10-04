package com.coelho.brasileiro.expensetrack.handle.actions.user;

import com.coelho.brasileiro.expensetrack.exception.BusinessException;
import com.coelho.brasileiro.expensetrack.model.User;
import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.repository.UserRepository;
import org.springframework.stereotype.Component;

import static com.coelho.brasileiro.expensetrack.util.BusinessCode.UserCodes.USER_OR_PASSWORD_INVALID;
import static com.coelho.brasileiro.expensetrack.util.Constants.User.USER;

@Component
public class FindUserByEmailAndPasswordHandler extends AbstractHandler {

    private final UserRepository userRepository;

    public FindUserByEmailAndPasswordHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doHandle(Context context) {
        User userToFind = context.getEntity(USER, User.class);
        User userFound = this.userRepository.findByEmailAndPassword(userToFind.getEmail(),
                userToFind.getPassword()).orElseThrow(
                () -> new BusinessException(USER_OR_PASSWORD_INVALID)
        );
        context.setEntity(USER, userFound);
    }
}
