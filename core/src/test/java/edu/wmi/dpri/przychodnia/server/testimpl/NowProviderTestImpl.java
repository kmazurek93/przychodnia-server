package edu.wmi.dpri.przychodnia.server.testimpl;

import edu.wmi.dpri.przychodnia.server.visits.service.NowProvider;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Profile("integration")
@Component
public class NowProviderTestImpl implements NowProvider {

    public static final DateTime NOW_FOR_TEST_PURPOSES = new DateTime(2016, 12, 1, 0, 0);

    @Override
    public DateTime now() {
        return NOW_FOR_TEST_PURPOSES;
    }
}
