package com.coelho.brasileiro.expensetrack.handle.actions.user;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.model.User;
import com.coelho.brasileiro.expensetrack.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.coelho.brasileiro.expensetrack.util.Constants.User.USER;

@Component
public class SaveUserHandler extends AbstractHandler {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SaveUserHandler(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void doHandle(Context context) {
        User user = context.getEntity(USER, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getCreatedAt() == null){
            user.setCreatedAt(LocalDateTime.now());
        }
        User userSaved = this.userRepository.save(user);
        context.setEntity(USER, userSaved);
    }
}
