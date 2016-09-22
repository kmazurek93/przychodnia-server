package edu.wmi.dpri.przychodnia.server.usermanagement.providers;

import edu.wmi.dpri.przychodnia.server.entity.Person;
import edu.wmi.dpri.przychodnia.server.entity.Role;
import edu.wmi.dpri.przychodnia.server.entity.User;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static edu.wmi.dpri.przychodnia.server.entity.builder.PersonBuilder.aPerson;
import static edu.wmi.dpri.przychodnia.server.entity.builder.UserBuilder.anUser;

/**
 * Created by kmazu on 24.08.2016.
 */
public class SampleDataProvider {

    public static final String APARTMENT = "445";
    public static final String CITY = "CITY";
    public static final String COUNTRY = "COUNTRY";
    public static final String HOUSE = "55A";
    public static final String POST_CODE = "12-222";
    public static final String A_PROVINCE = "A_PROVINCE";
    public static final String STREET = "STREET";
    public static final String PESEL_BASE = "123456789";
    public static final String USER = "USER";
    public static final String PATIENT = "PATIENT";

    public static List<Role> getSampleRoleList() {

        Role one = new Role();
        one.setName(USER);
        one.setId(1);
        Role two = new Role();
        two.setName(PATIENT);
        two.setId(2);
        return newArrayList(one, two);
    }

    public static List<User> getSampleUserList(String fNameTemplate, String lNameTemplate) {
        List<User> users = newArrayList();
        for (int i = 0; i < 3; i++) {
            User child = anUser().withId((long) i).withPerson(getPerson(fNameTemplate + i,
                    lNameTemplate + i)).build();
            users.add(child);
        }
        return users;
    }

    public static Person getPerson(String firstName, String lastName) {
        return aPerson().withFirstName(firstName).withLastName(lastName).build();
    }


}
