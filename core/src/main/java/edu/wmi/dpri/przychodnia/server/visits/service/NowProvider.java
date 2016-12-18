package edu.wmi.dpri.przychodnia.server.visits.service;

import org.joda.time.DateTime;

/**
 * Created by lupus on 18.12.16.
 */
public interface NowProvider {

    DateTime now();
}
