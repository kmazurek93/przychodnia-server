package edu.wmi.dpri.przychodnia.server.availability.web.service;

import edu.wmi.dpri.przychodnia.commons.availability.webmodel.DoctorAvailabilityWebModel;
import edu.wmi.dpri.przychodnia.commons.availability.webmodel.TimeWindowWebModel;
import edu.wmi.dpri.przychodnia.server.availability.function.AvailabilityToWebModelFunction;
import edu.wmi.dpri.przychodnia.server.availability.function.TimeWindowToWebModelFunction;
import edu.wmi.dpri.przychodnia.server.availability.service.AvailabilityService;
import edu.wmi.dpri.przychodnia.server.entity.Availability;
import edu.wmi.dpri.przychodnia.server.entity.TimeWindow;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth.ForbiddenException;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.verification.UserVerificationService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.generators.ErrorMessageGenerator.getForbiddenErrorMessage;
import static edu.wmi.dpri.przychodnia.server.security.model.RoleAuthority.ROLE_ADMIN;

/**
 * Created by lupus on 10.01.17.
 */
@Component
public class AvailabilityManagementWebService {

    @Inject
    private UserVerificationService userVerificationService;

    @Inject
    private AvailabilityService availabilityService;

    @Inject
    private TimeWindowToWebModelFunction timeWindowToWebModelFunction;
    @Inject
    private AvailabilityToWebModelFunction availabilityToWebModelFunction;

    public List<TimeWindowWebModel> getTimeWindows() {
        List<TimeWindow> timeWindows = availabilityService.getTimeWindows();
        return timeWindowToWebModelFunction.convertAll(timeWindows);
    }

    public List<DoctorAvailabilityWebModel> getAllAvailabilitiesForDoctor(Long doctorId) {
        List<Availability> availabilities = availabilityService.getDoctorAvailabilities(doctorId);
        return availabilityToWebModelFunction.convertAllWithDoctorId(availabilities, doctorId);
    }


    public DoctorAvailabilityWebModel createDoctorAvailabilityWebModel(DoctorAvailabilityWebModel model) {
        boolean isAdmin = userVerificationService.verifyIfHasAuthority(ROLE_ADMIN);
        if (isAdmin) {
            Availability availability = availabilityService.createDoctorAvailability(model);
            DoctorAvailabilityWebModel outcome = availabilityToWebModelFunction.apply(availability);
            assert outcome != null;
            outcome.setDoctorId(model.getDoctorId());
            return outcome;
        } else {
            ErrorMessage errorMessage = getForbiddenErrorMessage("INSUFFICIENT_PRIVILEGES");
            throw new ForbiddenException(errorMessage);
        }
    }

    public DoctorAvailabilityWebModel updateDoctorAvailability(DoctorAvailabilityWebModel model) {
        boolean isAdmin = userVerificationService.verifyIfHasAuthority(ROLE_ADMIN);
        if (isAdmin) {
            Availability availability = availabilityService.updateDoctorAvailability(model);
            DoctorAvailabilityWebModel outcome = availabilityToWebModelFunction.apply(availability);
            assert outcome != null;
            outcome.setDoctorId(model.getDoctorId());
            return outcome;
        } else {
            ErrorMessage errorMessage = getForbiddenErrorMessage("INSUFFICIENT_PRIVILEGES");
            throw new ForbiddenException(errorMessage);
        }
    }

    public void deleteAvailability(Long availabilityId, Long doctorId) {
        boolean isAdmin = userVerificationService.verifyIfHasAuthority(ROLE_ADMIN);
        if (isAdmin) {
            availabilityService.deleteDoctorAvailability(availabilityId, doctorId);
        } else {
            ErrorMessage errorMessage = getForbiddenErrorMessage("INSUFFICIENT_PRIVILEGES");
            throw new ForbiddenException(errorMessage);
        }
    }
}