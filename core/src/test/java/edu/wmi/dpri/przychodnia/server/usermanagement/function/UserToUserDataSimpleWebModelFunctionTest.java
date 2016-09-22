package edu.wmi.dpri.przychodnia.server.usermanagement.function;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserDataSimpleWebModel;
import edu.wmi.dpri.przychodnia.server.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static edu.wmi.dpri.przychodnia.server.entity.builder.PersonBuilder.aPerson;
import static edu.wmi.dpri.przychodnia.server.usermanagement.providers.SampleDataProvider.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by kmazu on 24.08.2016.
 */
public class UserToUserDataSimpleWebModelFunctionTest {


    public static final String USER_DOMAIN_COM = "user@domain.com";
    public static final String U_123 = "u123";
    public static final String JOZEK = "Jozek";
    public static final String PIERDZISTOLEK = "Pierdzistolek";
    public static final String ABC = "ABC";
    public static final String DEF = "DEF";
    public static final String PESEL = "12345678901";
    public static final long ID = 123;

    private UserToUserDataSimpleWebModelFunction tested = new UserToUserDataSimpleWebModelFunction();

    @Test
    public void apply() throws Exception {
        //given
        User user = getSampleUser();
        //when
        UserDataSimpleWebModel actual = tested.apply(user);
        //then
        assertThat(actual.getEmailAddress()).isEqualTo(USER_DOMAIN_COM);
        assertThat(actual.getFirstName()).isEqualTo(JOZEK);
        assertThat(actual.getLastName()).isEqualTo(PIERDZISTOLEK);
        assertThat(actual.getMiddleName()).isNullOrEmpty();
        assertThat(actual.getUserRoles()).containsOnly(USER, PATIENT);
        assertThat(actual.getPESEL()).isEqualTo(PESEL);
        assertThat(actual.getChildAccountNames()).containsOnly(getExpectedChildAccountName(0),
                getExpectedChildAccountName(1), getExpectedChildAccountName(2));
        assertThat(actual.getParentAccountNames()).containsOnly(getExpectedParentAccountName(0),
                getExpectedParentAccountName(1), getExpectedParentAccountName(2));
        assertThat(actual.getUserId()).isEqualTo(ID);

    }

    private User getSampleUser() {
        User user = new User();
        user.setActive(true);
        user.setId(ID);
        user.setEmailAddress(USER_DOMAIN_COM);
        user.setLogin(U_123);
        user.setPerson(aPerson().withFirstName(JOZEK).withPESEL(PESEL).withLastName(PIERDZISTOLEK).build());
        user.setChildUsers(getSampleUserList(ABC, DEF));
        user.setParentUsers(getSampleUserList(DEF, ABC));
        user.setRoles(getSampleRoleList());
        return user;
    }

    private String getExpectedChildAccountName(int i) {
        return StringUtils.join(ABC, i, " ", DEF, i);
    }

    private String getExpectedParentAccountName(int i) {
        return StringUtils.join(DEF, i, " ", ABC, i);
    }

}