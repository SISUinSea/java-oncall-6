package oncall.controller;

import static oncall.view.InputView.readHolidayScheduleInput;
import static oncall.view.InputView.readMonthInput;
import static oncall.view.InputView.readWeekScheduleInput;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.Supplier;
import oncall.model.Calendar;
import oncall.model.Schedule;
import oncall.util.CustomException;
import oncall.util.ErrorMessage;
import oncall.view.OutputView;

public class Controller {
    public static void run() {
        Calendar calendar = getCalendar();
        TotalSchedule totalSchedule = getTotalSchedule();

        validateScheduleWorker(totalSchedule);
        List<String> result = MonthScheduleGenerator.generateMonthSchedule(calendar,
                totalSchedule);

        OutputView.printMonthSchedule(calendar, result);
    }

    private static TotalSchedule getTotalSchedule() {
        Schedule weekDaySchedule = getWeekDaySchedule();
        Schedule holidaySchedule = getHolidaySchedule();

        return new TotalSchedule(weekDaySchedule, holidaySchedule);
    }

    private static void validateScheduleWorker(TotalSchedule totalSchedule) {
        Schedule weekDaySchedule = totalSchedule.weekday();
        Schedule holidaySchedule = totalSchedule.holiday();
        Set<String> totalScheduleWorker = new HashSet<>();
        totalScheduleWorker.addAll(weekDaySchedule.getSchedule());
        totalScheduleWorker.addAll(holidaySchedule.getSchedule());
        if (totalScheduleWorker.size() > 35 || totalScheduleWorker.size() < 5) {
            throw new CustomException(ErrorMessage.INVALID_INPUT);
        }
    }

    private static Schedule getHolidaySchedule() {
        return (Schedule) retry(() -> {
            String holidayWorkScheduleInput = readHolidayScheduleInput();
            return new Schedule(holidayWorkScheduleInput);
        });
    }

    private static Schedule getWeekDaySchedule() {
        return (Schedule) retry(() -> {
            String weekWorkScheduleInput = readWeekScheduleInput();
            return new Schedule(weekWorkScheduleInput);
        });
    }


    private static Calendar getCalendar() {
        return (Calendar) retry(() -> {
            String monthInput = readMonthInput();
            StringTokenizer st = new StringTokenizer(monthInput, ",");  // 예외 처리 해야 함
            Integer month = Integer.parseInt(st.nextToken());
            String firstDayOfWeek = st.nextToken();
            Calendar calendar = new Calendar(month, firstDayOfWeek);
            return calendar;
        });
    }

    private static Object retry(Supplier supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (CustomException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
