package edu.wmi.dpri.przychodnia.server.visits.service;

import edu.wmi.dpri.przychodnia.commons.visits.webmodel.SimpleVisitWebModel;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth.ForbiddenException;
import edu.wmi.dpri.przychodnia.server.integration.rule.DbScriptRule;
import org.joda.time.DateTime;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

/**
 * Created by lupus on 01.01.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"integration", "secure"})
public class VisitCreationServiceIntegTest {

    public static final DateTime FREE_DATE = new DateTime(2016, 12, 14, 9, 30);
    public static final DateTime OCCUPIED_DATE = new DateTime(2016, 12, 14, 8, 0);
    public static final DateTime UNAVAILABLE_DATE = new DateTime(2016, 12, 17, 8, 0);
    public static final DateTime HOLIDAY_DATE = new DateTime(2016, 12, 25, 8, 0);

    @Rule
    @Inject
    public DbScriptRule rule;

    @Inject
    private VisitCreationService tested;

    @Test
    public void shouldCheckIfWindowIsAvailable() throws Exception {
        //given
        SimpleVisitWebModel model = new SimpleVisitWebModel();
        model.setDoctorId(1L);
        model.setStart(FREE_DATE.getMillis());

        //when
        tested.checkIfVisitWindowIsAvailable(model);
        // then
        // nothing should happen
    }

    @Test(expected = ForbiddenException.class)
    public void shouldThrowExceptionIfTimeWindowIsOccupied() throws Exception {
        //given
        SimpleVisitWebModel model = new SimpleVisitWebModel();
        model.setDoctorId(1L);
        model.setStart(OCCUPIED_DATE.getMillis());

        //when
        tested.checkIfVisitWindowIsAvailable(model);
        // then
        // exception
    }

    @Test(expected = ForbiddenException.class)
    public void shouldThrowExceptionIfDoctorIsNotAvailable() throws Exception {
        //given
        SimpleVisitWebModel model = new SimpleVisitWebModel();
        model.setDoctorId(1L);
        model.setStart(UNAVAILABLE_DATE.getMillis());

        //when
        tested.checkIfVisitWindowIsAvailable(model);
        // then
        // exception
    }
    @Test(expected = ForbiddenException.class)
    public void shouldThrowExceptionIfHoliday() throws Exception {
        //given
        SimpleVisitWebModel model = new SimpleVisitWebModel();
        model.setDoctorId(1L);
        model.setStart(HOLIDAY_DATE.getMillis());

        //when
        tested.checkIfVisitWindowIsAvailable(model);
        // then
        // exception
    }

}