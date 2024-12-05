package oncall.controller;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import oncall.model.Calendar;

public class MonthScheduleGenerator {
    public static List<String> generateMonthSchedule(Calendar calendar, Deque<String> weekdaySchedule,
                                                     Deque<String> holidaySchedule) {
        // 일단 구현 먼저! 리팩토링은 나중에!
        List<String> result = new ArrayList<>();
        result.add(generateFirstDaySchedule(calendar, weekdaySchedule, holidaySchedule));
        for (int i = 2; i <= calendar.getEndDate(); i++) {
            String lastWorker = result.get(result.size() - 1);
            result.add(generateOneDaySchedule(calendar, weekdaySchedule, holidaySchedule, i, lastWorker));
        }
        System.out.println(result);
        return result;
    }

    private static String generateFirstDaySchedule(Calendar calendar, Deque<String> weekdaySchedule,
                                                   Deque<String> holidaySchedule) {
        // 맨 처음이라면 그냥 평/휴일에 따라서 근무자 추가
        String daySchedule = calendar.getMonth() + "월" + " " + 1 + "일" + " " + calendar.getDayOfWeekName(1) + " ";
        String todayWorker = "";
        if (calendar.isHoliday(1)) {
            todayWorker = holidaySchedule.remove();
        } else {
            todayWorker = weekdaySchedule.remove();
        }
        daySchedule += todayWorker;
        return daySchedule;
    }

    private static String generateOneDaySchedule(Calendar calendar, Deque<String> weekdaySchedule,
                                                   Deque<String> holidaySchedule, Integer day, String lastWorker) {
        // 맨 처음이라면 평/휴일을 고려해서 근무 스케쥴 작성하나, 어제 근무한 사람도 생각할 것!
        String daySchedule = calendar.getMonth() + "월" + " " + day + "일" + " " + calendar.getDayOfWeekName(day) + " ";
        String todayWorker = "";
        if (calendar.isHoliday(day)) {
            todayWorker = holidaySchedule.remove();
            if (lastWorker.equals(todayWorker)) {
                todayWorker = holidaySchedule.remove();
                holidaySchedule.addFirst(lastWorker);
            }
        } else {
            todayWorker = weekdaySchedule.remove();
            if (lastWorker.equals(todayWorker)) {
                todayWorker = weekdaySchedule.remove();
                weekdaySchedule.addFirst(lastWorker);
            }
        }

        daySchedule += todayWorker;
        return daySchedule;
    }


}
