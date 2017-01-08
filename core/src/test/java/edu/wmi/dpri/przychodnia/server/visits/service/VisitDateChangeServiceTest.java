package edu.wmi.dpri.przychodnia.server.visits.service;

import edu.wmi.dpri.przychodnia.commons.visits.webmodel.VisitDateChangeModel;
import edu.wmi.dpri.przychodnia.server.entity.User;
import edu.wmi.dpri.przychodnia.server.entity.Visit;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth.ForbiddenException;
import edu.wmi.dpri.przychodnia.server.integration.rule.DbScriptRule;
import edu.wmi.dpri.przychodnia.server.repository.VisitRepository;
import edu.wmi.dpri.przychodnia.server.security.jwt.ClinicJwtAuthToken;
import edu.wmi.dpri.przychodnia.server.security.model.UserContext;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.UserService;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static edu.wmi.dpri.przychodnia.server.security.model.RoleAuthority.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by lupus on 08.01.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"integration", "secure"})
public class VisitDateChangeServiceTest {

    public static final long VISIT_ID = 5L;
    public static final long PATIENT_ID = 2L;
    public static final LocalTime EXPECTED_VISIT_TIME = new LocalTime(9, 30);
    private static final List<String> ROLES = newArrayList(ROLE_ADMIN, ROLE_DOCTOR, ROLE_STAFF);
    public static final long USER_ID = 3L;
    public static final DateTime FREE_DATE = new DateTime(2016, 12, 14, 9, 30, 0);
    public static final DateTime OCCUPIED_DATE = new DateTime(2016, 12, 14, 8, 30, 0);

    @Rule
    @Inject
    public DbScriptRule rule;

    @Inject
    private VisitDateChangeService tested;

    @Inject
    private VisitRepository repository;
    @Inject
    private UserService userService;

    @Test
    public void shouldChangeVisitDate() throws Exception {
        //given
        VisitDateChangeModel model = new VisitDateChangeModel();
        model.setVisitId(VISIT_ID);
        model.setNewStartDate(FREE_DATE.getMillis());

        UserContext userContext = createUserContext();
        SecurityContext ctxt = SecurityContextHolder.createEmptyContext();
        ctxt.setAuthentication(new ClinicJwtAuthToken(userContext, userContext.getAuthorities()));
        SecurityContextHolder.setContext(ctxt);

        //when
        Visit visit = tested.changeVisitDate(model);
        //then
        assertThat(visit).isNotNull();
        assertThat(visit.getId()).isNotEqualTo(VISIT_ID);
        assertThat(visit.getPatient().getId()).isEqualTo(PATIENT_ID);
        assertThat(visit.getTimeWindow().getStartTime()).isEqualTo(EXPECTED_VISIT_TIME);
    }

    private UserContext createUserContext() {
        User userById = userService.getUserById(USER_ID);
        return UserContext.create(userById, userById.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList()));
    }


    @Test(expected = ForbiddenException.class)
    public void shouldNotChangeVisitDateIfWindowIsOccupied() {
        //given
        VisitDateChangeModel model = new VisitDateChangeModel();
        model.setVisitId(VISIT_ID);
        model.setNewStartDate(OCCUPIED_DATE.getMillis());

        UserContext userContext = createUserContext();
        SecurityContext ctxt = SecurityContextHolder.createEmptyContext();
        ctxt.setAuthentication(new ClinicJwtAuthToken(userContext, userContext.getAuthorities()));
        SecurityContextHolder.setContext(ctxt);

        //when
        tested.changeVisitDate(model);
        //then exception should be thrown
    }
}