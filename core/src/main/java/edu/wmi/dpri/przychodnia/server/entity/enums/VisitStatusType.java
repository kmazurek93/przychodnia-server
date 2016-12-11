package edu.wmi.dpri.przychodnia.server.entity.enums;

/**
 * Created by khartv on 29.11.2016.
 */
public enum VisitStatusType {
    NEW(0), CONFIRMED(1), POSTPONED(2), DELETED(3);
    private int ordinal;

    private VisitStatusType(int ordinal) {
        this.ordinal = ordinal;
    }

    public int getOrdinal() {
        return this.ordinal;
    }
}
