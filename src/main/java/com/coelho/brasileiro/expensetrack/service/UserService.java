package com.coelho.brasileiro.expensetrack.service;

import com.coelho.brasileiro.expensetrack.dto.UserDto;
import com.coelho.brasileiro.expensetrack.context.DefaultContext;
import com.coelho.brasileiro.expensetrack.dto.TokenDto;

import com.coelho.brasileiro.expensetrack.input.Input;
import com.coelho.brasileiro.expensetrack.input.LoginInput;
import com.coelho.brasileiro.expensetrack.input.UserInput;
import com.coelho.brasileiro.expensetrack.input.UserUpdate;
import com.coelho.brasileiro.expensetrack.flow.user.DoLoginFlowBuilder;
import com.coelho.brasileiro.expensetrack.flow.user.RegisterUserFlowBuilder;
import com.coelho.brasileiro.expensetrack.flow.user.UpdateUserFlowBuilder;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.repository.UserRepository;
import com.coelho.brasileiro.expensetrack.validator.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.coelho.brasileiro.expensetrack.util.Constants.Token.TOKEN_DTO;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final Converter converter = Converter.INSTANCE;

    @Autowired
    private InputValidator<Input> inputValidator;

    private final DoLoginFlowBuilder doLoginFlowBuilder;

    private final RegisterUserFlowBuilder registerUserFlowBuilder;

    private final UpdateUserFlowBuilder updateUserFlowBuilder;

    public UserService(UserRepository userRepository,
                       DoLoginFlowBuilder doLoginFlowBuilder,
                       RegisterUserFlowBuilder registerUserFlowBuilder,
                       UpdateUserFlowBuilder updateUserFlowBuilder) {
        this.userRepository = userRepository;
        this.doLoginFlowBuilder = doLoginFlowBuilder;
        this.registerUserFlowBuilder = registerUserFlowBuilder;
        this.updateUserFlowBuilder = updateUserFlowBuilder;
    }

    public TokenDto login(LoginInput loginInputRequest) {
        DefaultContext context = DefaultContext.builder().build();
        context.setLoginInput(loginInputRequest);
        context.setEntityNameCurrent("USER");
        doLoginFlowBuilder.create(context).build().run();
        return context.getDto(TOKEN_DTO, TokenDto.class);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserDto signUp(UserInput userInput) {
        DefaultContext context = DefaultContext.builder().build();
        context.setUserInput(userInput);
        context.setEntityNameCurrent("USER");
        registerUserFlowBuilder.create(context).build().run();
        return context.getUserDto();
    }

    public Object update(UUID id, UserUpdate userRequest) {
        DefaultContext context = DefaultContext.builder().build();
        context.setUserUpdate(userRequest);
        context.setEntityNameCurrent("USER");
        updateUserFlowBuilder.create(context).build().run();
        return context.getUserDto();
    }
}
