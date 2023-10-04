package com.coelho.brasileiro.expensetrack.handle.actions.user;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.exception.BusinessException;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.model.User;
import com.coelho.brasileiro.expensetrack.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.coelho.brasileiro.expensetrack.util.BusinessCode.UserCodes.USER_ALREADY_EXISTS;
import static com.coelho.brasileiro.expensetrack.util.Constants.User.USER;

@Component
public class SaveUserHandler extends AbstractHandler {

    private final UserRepository userRepository;

    public SaveUserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doHandle(Context context) {
        User user = context.getEntity(USER, User.class);
        if(user.getCreatedAt() == null){
            user.setCreatedAt(LocalDateTime.now());
        }
        User userSaved = this.userRepository.save(user);
        context.setEntity(USER, userSaved);
    }
}
