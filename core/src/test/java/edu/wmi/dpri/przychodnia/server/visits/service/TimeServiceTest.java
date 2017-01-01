package edu.wmi.dpri.przychodnia.server.visits.service;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by lupus on 18.12.16.
 */
public class TimeServiceTest {

    @Mock
    private NowProvider nowProvider;

    @InjectMocks
    private TimeService tested = new TimeService();

    @Before
    public void before() {
        initMocks(this);
    }

    @Test
    public void getSixMonthsLaterOnEndOfTheMonth() throws Exception {
        DateTime dateTime = new DateTime(2016, 7, 15, 0, 0);
        DateTime actual = tested.getSixMonthsLaterOnEndOfTheMonth(dateTime);
        assertThat(actual).isEqualByComparingTo(new DateTime(2016, 12, 31, 23, 59, 59, 999));
    }


}