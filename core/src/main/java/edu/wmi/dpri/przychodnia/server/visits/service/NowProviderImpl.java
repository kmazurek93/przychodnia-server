package edu.wmi.dpri.przychodnia.server.visits.service;

import org.joda.time.DateTime;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Component
@Profile("!integration")
public class NowProviderImpl implements NowProvider {
    @Override
    public DateTime now() {
        return new DateTime();
    }
}
