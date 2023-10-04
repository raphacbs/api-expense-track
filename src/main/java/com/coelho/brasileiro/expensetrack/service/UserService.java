package com.coelho.brasileiro.expensetrack.service;

import com.coelho.brasileiro.expensetrack.dto.UserDTO;
import com.coelho.brasileiro.expensetrack.dto.request.InputRequest;
import com.coelho.brasileiro.expensetrack.context.DefaultContext;
import com.coelho.brasileiro.expensetrack.dto.TokenDto;

import com.coelho.brasileiro.expensetrack.dto.request.LoginRequest;
import com.coelho.brasileiro.expensetrack.dto.request.UserRequest;
import com.coelho.brasileiro.expensetrack.flow.user.DoLoginFlowBuilder;
import com.coelho.brasileiro.expensetrack.flow.user.RegisterUserFlowBuilder;
import com.coelho.brasileiro.expensetrack.mapper.UserMapper;
import com.coelho.brasileiro.expensetrack.repository.UserRepository;
import com.coelho.brasileiro.expensetrack.validator.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.coelho.brasileiro.expensetrack.util.Constants.Token.TOKEN_DTO;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Autowired
    private InputValidator<InputRequest> inputValidator;

    private final DoLoginFlowBuilder doLoginFlowBuilder;

    private final RegisterUserFlowBuilder registerUserFlowBuilder;

    public UserService(UserRepository userRepository,
                       DoLoginFlowBuilder doLoginFlowBuilder,
                       RegisterUserFlowBuilder registerUserFlowBuilder) {
        this.userRepository = userRepository;
        this.doLoginFlowBuilder = doLoginFlowBuilder;
        this.registerUserFlowBuilder = registerUserFlowBuilder;
    }

    public TokenDto login(LoginRequest loginRequest) {
        DefaultContext context = DefaultContext.builder().build();
        context.setLoginRequest(loginRequest);
        doLoginFlowBuilder.create(context).build().run();
        return context.getDto(TOKEN_DTO, TokenDto.class);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserDTO signUp(UserRequest userRequest) {
        DefaultContext context = DefaultContext.builder().build();
        context.setUserRequest(userRequest);
        registerUserFlowBuilder.create(context).build().run();
        return context.getUserDto();
    }
}
