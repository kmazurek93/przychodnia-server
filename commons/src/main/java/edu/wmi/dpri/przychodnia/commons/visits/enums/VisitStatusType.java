package edu.wmi.dpri.przychodnia.commons.visits.enums;


public enum VisitStatusType {
    NEW(0), HAPPENED(1), HOME_VISIT(2), CANCELLED(3);
    private int ordinal;

    VisitStatusType(int ordinal) {
        this.ordinal = ordinal;
    }

    public int getOrdinal() {
        return this.ordinal;
    }

}
