package edu.wmi.dpri.przychodnia.commons.usermanagement;

import java.util.ArrayList;

import static jersey.repackaged.com.google.common.collect.Lists.newArrayList;


public interface UserManagementConstants {
    String API_PATH = "/userManagement";
    String USER = "ROLE_USER";
    String PATIENT = "ROLE_PATIENT";
    ArrayList<String> DEFAULT_ROLES_FOR_NEW_USER = newArrayList(USER, PATIENT);
}
