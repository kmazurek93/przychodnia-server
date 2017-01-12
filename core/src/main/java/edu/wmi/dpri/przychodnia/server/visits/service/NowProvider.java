package edu.wmi.dpri.przychodnia.server.visits.service;

import org.joda.time.DateTime;


public interface NowProvider {

    DateTime now();
}
