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
        result.add(""); // 1-based indexing
        String lastWorker = "";
        for (int i = 1; i <= calendar.getEndDate(); i++) {
            result.add(generateOneDaySchedule(calendar, weekdaySchedule, holidaySchedule, i, lastWorker));
            lastWorker = result.get(result.size() - 1);
        }
        System.out.println(result);
        return result;
    }

    private static String generateOneDaySchedule(Calendar calendar, Deque<String> weekdaySchedule,
                                                   Deque<String> holidaySchedule, Integer day, String lastWorker) {
        // 맨 처음이라면 평/휴일을 고려해서 근무 스케쥴 작성하나, 어제 근무한 사람도 생각할 것!

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

        return todayWorker;
    }
}
