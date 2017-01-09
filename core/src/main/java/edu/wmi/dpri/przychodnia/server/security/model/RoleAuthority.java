package edu.wmi.dpri.przychodnia.server.security.model;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by lupus on 29.10.16.
 */
public interface RoleAuthority {
    String ROLE_ADMIN = "ROLE_ADMIN";
    String ROLE_USER = "ROLE_USER";
    String ROLE_PATIENT = "ROLE_PATIENT";
    String ROLE_STAFF = "ROLE_STAFF";
    String ROLE_DOCTOR = "ROLE_DOCTOR";
    List<String> STAFF_OR_ADMIN = newArrayList(ROLE_STAFF, ROLE_ADMIN);
    List<String> STAFF_ROLES = newArrayList(ROLE_STAFF, ROLE_USER);
    List<String> ADMIN_ROLES = newArrayList(ROLE_ADMIN, ROLE_STAFF, ROLE_USER);
    List<String> PATIENT_ROLES = newArrayList(ROLE_USER, ROLE_PATIENT);
    List<String> DOCTOR_ROLES = newArrayList(ROLE_USER, ROLE_DOCTOR, ROLE_STAFF);
    List<String> ALL_ROLES = newArrayList(ROLE_PATIENT, ROLE_USER, ROLE_ADMIN, ROLE_DOCTOR, ROLE_STAFF);
}
