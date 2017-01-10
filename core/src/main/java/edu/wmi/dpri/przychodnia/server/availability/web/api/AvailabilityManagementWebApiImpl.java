package edu.wmi.dpri.przychodnia.server.availability.web.api;

import edu.wmi.dpri.przychodnia.commons.availability.webapi.AvailabilityManagementWebApi;
import edu.wmi.dpri.przychodnia.commons.availability.webmodel.DoctorAvailabilityWebModel;
import edu.wmi.dpri.przychodnia.commons.availability.webmodel.TimeWindowWebModel;
import edu.wmi.dpri.przychodnia.server.availability.web.service.AvailabilityManagementWebService;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by lupus on 10.01.17.
 */
public class AvailabilityManagementWebApiImpl implements AvailabilityManagementWebApi {

    @Inject
    private AvailabilityManagementWebService availabilityManagementWebService;

    @Override
    public List<TimeWindowWebModel> getTimeWindows() {
        return availabilityManagementWebService.getTimeWindows();
    }

    @Override
    public List<DoctorAvailabilityWebModel> getAvailabilitiesForDoctor(Long doctorId) {
        return availabilityManagementWebService.getAllAvailabilitiesForDoctor(doctorId);
    }

    @Override
    public DoctorAvailabilityWebModel createDoctorAvailabilityWebModel(DoctorAvailabilityWebModel model) {
        return availabilityManagementWebService.createDoctorAvailabilityWebModel(model);
    }

    @Override
    public DoctorAvailabilityWebModel updateDoctorAvailability(DoctorAvailabilityWebModel model) {
        return availabilityManagementWebService.updateDoctorAvailability(model);
    }

    @Override
    public void deleteDoctorAvailability(Long availabilityId, Long doctorId) {
        availabilityManagementWebService.deleteAvailability(availabilityId, doctorId);
    }


}
