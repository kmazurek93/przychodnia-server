package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.server.entity.User;
import edu.wmi.dpri.przychodnia.server.integration.rule.DbScriptRule;
import org.glassfish.jersey.internal.util.Base64;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"integration", "secure"})
public class AuthenticationServiceTest {

    public static final String USER12 = "user12";
    public static final byte[] BYTES = "user123".getBytes();
    public static final String ENCODED_PASSWORD = new String(Base64.encode(BYTES));
    public static final String FAKE_USER = "user90786";
    public static final String EMPTY_STRING = "";
    @Rule
    @Inject
    public DbScriptRule scriptRule;
    @Inject
    private AuthenticationService tested;

    @Test
    public void isPasswordValid() throws Exception {
        //given
        //when
        Boolean passwordValid = tested.isPasswordValid(USER12, ENCODED_PASSWORD);
        //then
        assertThat(passwordValid).isTrue();

    }
    @Test
    public void shouldReturnFalseIfuserDoesNotExists() {
        //when
        Boolean passwordValid = tested.isPasswordValid(FAKE_USER, "");
        //then
        assertThat(passwordValid).isFalse();
    }

    @Test
    public void authenticateAndReturnUser() throws Exception {
        //when
        User user = tested.authenticateAndReturnUser(USER12, ENCODED_PASSWORD);
        // then
        assertThat(user).isNotNull();
        assertThat(user.getLogin()).isEqualTo(USER12);
    }

    @Test(expected = BadCredentialsException.class)
    public void shouldThrowExceptionWhenBadCredentials() {
        //when
        tested.authenticateAndReturnUser(USER12, EMPTY_STRING);
        //then exception should be thrown
    }
    @Test(expected = BadCredentialsException.class)
    public void shouldThrowExceptionIfUserDoesNotExist() {
        //when
        tested.authenticateAndReturnUser(FAKE_USER, ENCODED_PASSWORD);
        //then exception should be thrown
    }

}