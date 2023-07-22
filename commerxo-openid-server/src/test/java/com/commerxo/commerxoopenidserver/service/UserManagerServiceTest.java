package com.commerxo.commerxoopenidserver.service;

import com.commerxo.commerxoopenidserver.api.user.UserRegisterRequest;
import com.commerxo.commerxoopenidserver.models.User;
import com.commerxo.commerxoopenidserver.repository.UserRepository;
import com.commerxo.commerxoopenidserver.service.impl.UserManagerServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserManagerServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserManagerServiceImpl userManagerService;

    @BeforeEach
    public void setUp(){
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        this.userManagerService = new UserManagerServiceImpl(this.passwordEncoder, this.repository);
    }

    @AfterEach
    public void clear(){
        this.passwordEncoder = null;
        this.userManagerService = null;
    }

    @Test
    public void createUserWhenRequestObjectIsNull(){
        assertThatIllegalArgumentException()
                .isThrownBy(() -> this.userManagerService.create(null))
                .withMessageContaining("UserRegisterRequest, can't be null!");
    }

    @Test
    void createUserWhenUsernameIsNullInRequest(){
        assertThatIllegalArgumentException()
                .isThrownBy(() -> this.userManagerService.create(UserRegisterRequest.builder()
                        .password("pass")
                        .emailId("email@test.com")
                        .build())
                ).withMessageContaining("Username can't be empty!");
    }

    @Test
    void createUserWhenUsernameIsNotPresentInRequest(){
        assertThatIllegalArgumentException()
                .isThrownBy(() -> this.userManagerService.create(UserRegisterRequest.builder()
                        .username("")
                        .password("pass")
                        .emailId("email@test.com")
                        .build())
                ).withMessageContaining("Username can't be empty!");
    }


    @Test
    void createUserWhenPasswordIsNullInRequest(){
        assertThatIllegalArgumentException()
                .isThrownBy(() -> this.userManagerService.create(UserRegisterRequest.builder()
                        .username("user")
                        .password(null)
                        .emailId("email@test.com")
                        .build())
                ).withMessageContaining("Password can't be empty!");
    }

    @Test
    void createUserWhenPasswordIsNotPresentInRequest(){
        assertThatIllegalArgumentException()
                .isThrownBy(() -> this.userManagerService.create(UserRegisterRequest.builder()
                        .username("username")
                        .password("")
                        .emailId("email@test.com")
                        .build())
                ).withMessageContaining("Password can't be empty!");
    }

    @Test
    void createUserWhenEmailIdIsNullInRequest(){
        assertThatIllegalArgumentException()
                .isThrownBy(() -> this.userManagerService.create(UserRegisterRequest.builder()
                        .username("user")
                        .password("pass")
                        .emailId(null)
                        .build())
                ).withMessageContaining("EmailId can't be empty!");
    }

    @Test
    void createUserWhenEmailIdIsNotPresentInRequest(){
        assertThatIllegalArgumentException()
                .isThrownBy(() -> this.userManagerService.create(UserRegisterRequest.builder()
                        .username("username")
                        .password("pass")
                        .emailId("")
                        .build())
                ).withMessageContaining("EmailId can't be empty!");
    }


    @Test
    public void createUserWhenUserRegisterRequestPresent(){
        UserRegisterRequest request = UserRegisterRequest.builder()
                .username("test")
                .password("test_password")
                .emailId("1234@test.com")
                .build();
        User createdUser = this.userManagerService.create(request);
        Mockito.when(repository.findByUsername(request.getUsername())).thenReturn(createdUser);
        assertThat(createdUser).isNotNull();
        assertThat(createdUser.getId()).isNotNull();
        assertThat(createdUser.getUsername()).isEqualTo(request.getUsername());
        assertThat(createdUser.getEmailId()).isEqualTo(request.getEmailId());
        assertThat(passwordEncoder.matches(request.getPassword(), createdUser.getPassword())).isTrue();
    }

}