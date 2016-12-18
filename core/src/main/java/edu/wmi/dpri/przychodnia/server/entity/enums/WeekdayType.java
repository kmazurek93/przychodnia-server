package edu.wmi.dpri.przychodnia.server.entity.enums;

/**
 * Created by khartv on 29.11.2016.
 */
public enum WeekdayType {
    MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6), SUNDAY(7);

    private int value;

    private WeekdayType(int value) {
        this.value = value;
    }

    public int getOrdinal() {
        return this.value;
    }

    public static WeekdayType createFromValue(int value) {
        if(value > 0 && value < 8) {
            return WeekdayType.values()[value];
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}
