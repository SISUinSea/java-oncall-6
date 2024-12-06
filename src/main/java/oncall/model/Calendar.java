package oncall.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import oncall.util.CustomException;
import oncall.util.DayOfWeek;
import oncall.util.ErrorMessage;
import oncall.util.Holiday;

public class Calendar {
    private final Integer month;
    private DayOfWeek firstDayOfWeek;  // todo. 더 공부할 것이 생겼어! >>enum을 좀 더 정밀하게 다룰 수 있어!(김민겸님 코드도 보자)
    private final Integer endDate;
    private final Set<Integer> endDate28 = new HashSet<>(List.of(2));
    private final Set<Integer> endDate31 = new HashSet<>(List.of(1,3,5,7,8,10,12));
    private final Set<Integer> endDate30 = new HashSet<>(List.of(4,6,9,11));

    public Calendar(Integer month, String firstDayOfWeek) {
        this.month = month;
        this.firstDayOfWeek = DayOfWeek.getDayOfWeek(firstDayOfWeek);
        this.endDate = getEndDateByMonth(month);
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
        throw new CustomException(ErrorMessage.INVALID_INPUT);
    }

    public Integer getEndDate() {
        return endDate;
    }

    public Integer getMonth() {
        return month;
    }

    public String getDayOfWeekName(Integer day) {
        return DayOfWeek.getDayOfWeek((firstDayOfWeek.ordinal() + day - 1) % 7).getName();
    }

    public boolean isHoliday(Integer day) {
        String dayOfWeekName = getDayOfWeekName(day);
        if (dayOfWeekName.equals(DayOfWeek.SUNDAY.getName()) || dayOfWeekName.equals(DayOfWeek.SATURDAY.getName())) {
            return true;
        }
        if (Holiday.isHoliday(month, day)) {
            return true;
        }
        return false;
    }

    public boolean isWeekDayHoliday(Integer day) {
        if (getDayOfWeekName(day).equals("토") || getDayOfWeekName(day).equals("일")) {
            return false;
        }
        if (Holiday.isHoliday(month, day)) {
            return true;
        }

        return false;
    }
}
