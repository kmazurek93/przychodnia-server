package edu.wmi.dpri.przychodnia.server.usermanagement.function;

import com.google.common.base.Function;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserDataSimpleModel;
import edu.wmi.dpri.przychodnia.server.entity.views.BaseUserData;
import edu.wmi.dpri.przychodnia.server.entity.views.EmployeesData;
import edu.wmi.dpri.przychodnia.server.entity.views.PatientsData;
import edu.wmi.dpri.przychodnia.server.entity.views.UsersData;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static edu.wmi.dpri.przychodnia.commons.usermanagement.builder.UserDataSimpleModelBuilder.anUserDataSimpleModel;

/**
 * Created by lupus on 11.12.16.
 */
@Component
public class BaseUserDataToUserDataSimpleModelFunction implements Function<BaseUserData, UserDataSimpleModel> {

    public static final String EMPLOYEE = "EMPLOYEE";
    public static final String PATIENT = "PATIENT";
    public static final String USER = "USER";
    public static final String DOCTOR = "DOCTOR";

    @Override
    public UserDataSimpleModel apply(BaseUserData input) {
        UserDataSimpleModel build = anUserDataSimpleModel()
                .withPesel(input.getPesel())
                .withName(input.getName())
                .withUserId(input.getUserId())
                .withEmail(input.getEmail())
                .withTelephone(input.getTelephone())
                .withAddress(input.getAddress())
                .withMailingAddress(input.getMailingAddress())
                .build();
        if (input instanceof EmployeesData) {
            if (((EmployeesData) input).getDoctorId() == null) {
                build.setEntityId(((EmployeesData) input).getId());
                build.setEntityType(EMPLOYEE);
            } else {
                build.setEntityId(((EmployeesData) input).getDoctorId());
                build.setEntityType(DOCTOR);
            }
        } else if (input instanceof PatientsData) {
            build.setEntityId(((PatientsData) input).getId());
            build.setEntityType(PATIENT);
        } else if (input instanceof UsersData) {
            build.setEntityId(((UsersData) input).getId());
            build.setEntityType(USER);
        }
        return build;
    }

    public List<UserDataSimpleModel> convertAll(Collection<? extends BaseUserData> input) {
        return input.stream().map(this::apply).collect(Collectors.toList());
    }
}
