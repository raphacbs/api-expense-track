package com.coelho.brasileiro.expensetrack.handle.user;


import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.exception.BusinessException;
import com.coelho.brasileiro.expensetrack.handle.actions.user.FindUserByEmailAndPasswordHandler;
import com.coelho.brasileiro.expensetrack.model.User;
import com.coelho.brasileiro.expensetrack.context.DefaultContext;
import com.coelho.brasileiro.expensetrack.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static com.coelho.brasileiro.expensetrack.utils.UserUtil.createUserBuilder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class FindUserByEmailAndPasswordHandlerTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    FindUserByEmailAndPasswordHandler findUserByEmailAndPasswordHandler;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findUserWithSucess(){
        User user = createUserBuilder().build();
        when(this.userRepository.findByEmailAndPassword(any(String.class), any(String.class)))
                .thenReturn(
                     Optional.of(user)
                );
        Context context = DefaultContext.builder().build();
        context.setEntity("USER", createUserBuilder().id(user.getId()).build());
        findUserByEmailAndPasswordHandler.handle(context);

        Assertions.assertNotNull(context.getEntity("USER", User.class));
        Assertions.assertEquals(context.getEntity("USER", User.class).getId(), user.getId());
    }
    @Test
    void findUserNotFound(){
        User user = createUserBuilder().build();
        when(this.userRepository.findByEmailAndPassword(any(String.class), any(String.class)))
                .thenReturn(
                     Optional.empty()
                );
        Context context = DefaultContext.builder().build();
        context.setEntity("USER", createUserBuilder().id(user.getId()).build());

        BusinessException thrown = Assertions.assertThrows(
                BusinessException.class,
                () -> findUserByEmailAndPasswordHandler.handle(context)
        );


        Assertions.assertEquals(100010, thrown.getErrorCode());


    }
}
