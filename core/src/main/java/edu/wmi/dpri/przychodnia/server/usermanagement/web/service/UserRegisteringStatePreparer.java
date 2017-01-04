package edu.wmi.dpri.przychodnia.server.usermanagement.web.service;

import edu.wmi.dpri.przychodnia.commons.publics.webmodel.RegistrationInputWebModel;
import edu.wmi.dpri.przychodnia.server.entity.IdType;
import edu.wmi.dpri.przychodnia.server.entity.Role;
import edu.wmi.dpri.przychodnia.server.entity.Sex;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.IdTypeService;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.RoleService;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.SexService;
import edu.wmi.dpri.przychodnia.server.usermanagement.state.UserRegisteringState;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

import static edu.wmi.dpri.przychodnia.commons.usermanagement.UserManagementConstants.DEFAULT_ROLES_FOR_NEW_USER;
import static edu.wmi.dpri.przychodnia.server.usermanagement.state.UserRegisteringStateBuilder.anUserRegisteringState;

/**
 * Created by lupus on 18.10.16.
 */
@Component
public class UserRegisteringStatePreparer {

    @Inject
    private IdTypeService idTypeService;
    @Inject
    private RoleService roleService;
    @Inject
    private SexService sexService;


    public UserRegisteringState prepareUserRegisteringState(RegistrationInputWebModel
                                                                    inputWebModel) {
        UserRegisteringState state = anUserRegisteringState().withRegistrationInputWebModel(inputWebModel).build();
        setTargetIdType(inputWebModel, state);
        setTargetSex(inputWebModel, state);
        setTargetRoles(state);
        return state;
    }

    private void setTargetIdType(RegistrationInputWebModel inputWebModel, UserRegisteringState state) {
        IdType targetIdType = idTypeService.getById(inputWebModel.getPersonalData().getIdTypeNo());
        state.setTargetIdType(targetIdType);
    }

    private void setTargetSex(RegistrationInputWebModel inputWebModel, UserRegisteringState state) {
        Sex targetSex = sexService.findOne(inputWebModel.getPersonalData().getSexId());
        state.setTargetSex(targetSex);
    }

    private void setTargetRoles(UserRegisteringState state) {
        List<Role> roles = roleService.findByNameIn(DEFAULT_ROLES_FOR_NEW_USER);
        state.setTargetRoles(roles);
    }
}
