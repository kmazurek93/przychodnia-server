package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.server.integration.rule.DbScriptRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by lupus on 08.01.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"integration", "secure"})
public class UserServiceTest {

    public static final String ADMIN_PRZYCHODNIA_COM = "admin@przychodnia.com";
    public static final String FAKE_EMAIL_NOPE_COM = "fake_email@nope.com";
    public static final String ADMIN = "admin";
    public static final String ADMIN_1235624 = "admin1235624";
    @Inject
    @Rule
    public DbScriptRule dbScriptRule;
    @Inject
    private UserService tested;


    @Test
    public void shouldReturnTrueIfemailExists() throws Exception {
        //when
        boolean actual = tested.emailExists(ADMIN_PRZYCHODNIA_COM);
        //then
        assertThat(actual).isTrue();

    }
    @Test
    public void shouldNotReturnTrueIfEmailDoesNotExist() throws Exception {
        //when
        boolean actual = tested.emailExists(FAKE_EMAIL_NOPE_COM);
        //then
        assertThat(actual).isFalse();

    }
    @Test
    public void usernameExists() throws Exception {
        //when
        boolean actual = tested.usernameExists(ADMIN);
        //then
        assertThat(actual).isTrue();
    }
    @Test
    public void usernameDoesNotExist() {
        //when
        boolean actual = tested.usernameExists(ADMIN_1235624);
        //then
        assertThat(actual).isFalse();
    }


}