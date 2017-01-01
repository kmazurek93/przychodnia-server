package edu.wmi.dpri.przychodnia.commons.visits.enums;

/**
 * Created by khartv on 29.11.2016.
 */
public enum VisitStatusType {
    NEW(0), HAPPENED(1), HOME_VISIT(2), CANCELLED(3);
    private int ordinal;

    private VisitStatusType(int ordinal) {
        this.ordinal = ordinal;
    }

    public int getOrdinal() {
        return this.ordinal;
    }

}
