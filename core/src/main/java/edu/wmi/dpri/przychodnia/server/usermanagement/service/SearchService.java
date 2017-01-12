package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserSearchWebModel;
import edu.wmi.dpri.przychodnia.server.entity.views.EmployeesData;
import edu.wmi.dpri.przychodnia.server.entity.views.PatientsData;
import edu.wmi.dpri.przychodnia.server.entity.views.UsersData;
import edu.wmi.dpri.przychodnia.server.repository.views.EmployeesDataRepository;
import edu.wmi.dpri.przychodnia.server.repository.views.PatientsDataRepository;
import edu.wmi.dpri.przychodnia.server.repository.views.UsersDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.springframework.data.domain.Sort.Direction.ASC;


@Component
public class SearchService {
    public static final Sort SORT_BY_NAME_ASCENDING = new Sort(ASC, "name");
    public static final String LIKE_ANY_MATCH = "%%";

    @Inject
    private UsersDataRepository usersDataRepository;
    @Inject
    private EmployeesDataRepository employeesDataRepository;
    @Inject
    private PatientsDataRepository patientsDataRepository;


    @Transactional(readOnly = true)
    public Page<UsersData> queryOnAllUsers(UserSearchWebModel model) {
        return usersDataRepository.queryOnAllPageable(LIKE_ANY_MATCH, model.getMail(), model.getTelephone(),
                model.getAddress(), model.getPesel(),
                model.getName(), new PageRequest(model.getPage(),
                        model.getSize(), SORT_BY_NAME_ASCENDING));
    }

    @Transactional(readOnly = true)
    public Page<EmployeesData> queryOnEmployees(UserSearchWebModel model) {
        return employeesDataRepository.queryOnAllPageable(LIKE_ANY_MATCH, model.getMail(), model.getTelephone(),
                model.getAddress(), model.getPesel(),
                model.getName(), new PageRequest(model.getPage(),
                        model.getSize(), SORT_BY_NAME_ASCENDING));
    }

    @Transactional(readOnly = true)
    public Page<PatientsData> queryOnPatients(UserSearchWebModel model) {
        return patientsDataRepository.queryOnAllPageable(LIKE_ANY_MATCH, model.getMail(), model.getTelephone(),
                model.getAddress(), model.getPesel(),
                model.getName(), new PageRequest(model.getPage(),
                        model.getSize(), SORT_BY_NAME_ASCENDING));
    }
}
