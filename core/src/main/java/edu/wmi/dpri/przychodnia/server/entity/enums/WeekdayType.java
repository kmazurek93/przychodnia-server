package edu.wmi.dpri.przychodnia.server.entity.enums;

/**
 * Created by khartv on 29.11.2016.
 */
public enum WeekdayType {
    MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6), SUNDAY(7);

    private int ordinal;

    private WeekdayType(int ordinal) {
        this.ordinal = ordinal;
    }

    public int getOrdinal() {
        return this.ordinal;
    }

}
