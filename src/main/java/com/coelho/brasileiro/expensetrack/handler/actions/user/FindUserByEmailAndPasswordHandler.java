package com.coelho.brasileiro.expensetrack.handler.actions.user;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.exception.BusinessException;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.model.User;
import com.coelho.brasileiro.expensetrack.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.coelho.brasileiro.expensetrack.util.BusinessCode.UserCodes.EMAIL_NOT_FOUND;
import static com.coelho.brasileiro.expensetrack.util.BusinessCode.UserCodes.PASSWORD_INVALID;
import static com.coelho.brasileiro.expensetrack.util.Constants.User.USER;

@Component
public class FindUserByEmailAndPasswordHandler extends AbstractHandler {

    private final UserRepository userRepository;
    private final PasswordEncoder  passwordEncoder;

    public FindUserByEmailAndPasswordHandler(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void doHandle(Context context) {
        User userToFind = context.getEntity(USER, User.class);
        User userFound = this.userRepository.findByEmail(userToFind.getEmail()).orElseThrow(
                () -> new BusinessException(EMAIL_NOT_FOUND)
        );
        if (!passwordEncoder.matches(userToFind.getPassword(), userFound.getPassword())) {
            throw new BusinessException(PASSWORD_INVALID);
        }
        context.setEntity(USER, userFound);
    }
}
