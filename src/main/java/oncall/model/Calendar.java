package oncall.model;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Calendar {
    private final Integer month;
    private DayOfWeek firstDayOfWeek;
    private final Integer endDate;
    private final Set<Integer> endDate28 = new HashSet<>(List.of(2));
    private final Set<Integer> endDate31 = new HashSet<>(List.of(1,3,5,7,8,10,12));
    private final Set<Integer> endDate30 = new HashSet<>(List.of(4,6,9,11));

    public Calendar(Integer month, String firstDayOfWeek) {
        this.month = month;
        this.firstDayOfWeek = getDayOfWeekByName(firstDayOfWeek);
        this.endDate = getEndDateByMonth(month);
    }

    private static DayOfWeek getDayOfWeekByName(String name) {
        if (name.equals("일")) {
            return DayOfWeek.SUNDAY;
        }
        if (name.equals("월")) {
            return DayOfWeek.MONDAY;
        }
        if (name.equals("화")) {
            return DayOfWeek.TUESDAY;
        }
        if (name.equals("수")) {
            return DayOfWeek.WEDNESDAY;
        }
        if (name.equals("목")) {
            return DayOfWeek.THURSDAY;
        }
        if (name.equals("금")) {
            return DayOfWeek.FRIDAY;
        }
        if (name.equals("토")){
            return DayOfWeek.SATURDAY;
        }
        throw new IllegalArgumentException();
    }

    private Integer getEndDateByMonth(Integer month) {
        if (endDate28.contains(month)) {
            return 28;
        }
        if (endDate30.contains(month)) {
            return 30;
        }
        if (endDate31.contains(month)) {
            return 31;
        }
        throw new IllegalArgumentException();
    }

    public Integer getEndDate() {
        return endDate;
    }

    public Integer getMonth() {
        return month;
    }
}
