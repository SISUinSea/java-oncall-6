package oncall.view;

import java.util.List;
import java.util.stream.IntStream;
import oncall.model.Calendar;

public class OutputView {
    public static void printMonthSchedule(Calendar calendar, List<String> monthSchedule) {
        IntStream.range(1, calendar.getEndDate() + 1).forEach(day -> {
            printOneDaySchedule(calendar, monthSchedule, day);
        });

    }

    private static void printOneDaySchedule(Calendar calendar, List<String> monthSchedule, int day) {
        String daySchedule = calendar.getMonth() + "월" + " " + day + "일";
        if (calendar.isWeekDayHoliday(day)) {
            daySchedule += "(휴일)";
        }
        daySchedule +=  " " + calendar.getDayOfWeekName(day) + " ";
        daySchedule += monthSchedule.get(day);

        System.out.println(daySchedule);
    }
}
