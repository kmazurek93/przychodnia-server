package edu.wmi.dpri.przychodnia.server.entity.enums;

/**
 * Created by khartv on 29.11.2016.
 */
public enum WeekdayType {
    MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6), SUNDAY(7);

    int value;

    WeekdayType(int i) {
        this.value = i;
    }
    public int getValue() {
        return value;
    }
    public static WeekdayType fromInt(int i) {
        if(i<1 || i>7) throw new IllegalArgumentException();
        int j = i -1;
        return WeekdayType.values()[j];
    }
}
