package oncall;

import static oncall.view.InputView.readHolidayScheduleInput;
import static oncall.view.InputView.readMonthInput;
import static oncall.view.InputView.readWeekScheduleInput;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import net.bytebuddy.pool.TypePool.Resolution.Illegal;
import oncall.controller.MonthScheduleGenerator;
import oncall.model.Calendar;
import oncall.model.HolidaySchedule;
import oncall.model.WeekDaySchedule;
import oncall.util.Holiday;

public class Application {
    public static void main(String[] args) {
        Calendar calendar = getCalendar();
        Holiday holiday = new Holiday();
        WeekDaySchedule weekDaySchedule = getWeekDaySchedule();
        HolidaySchedule holidaySchedule = getHolidaySchedule();
        validateScheduleWorker(weekDaySchedule, holidaySchedule);
        List<String> result = MonthScheduleGenerator.generateMonthSchedule(calendar, weekDaySchedule.getEnoughScheduleAsDeque(),
                holidaySchedule.getEnoughScheduleAsDeque());

        result.stream().forEach(System.out::println);
    }

    private static void validateScheduleWorker(WeekDaySchedule weekDaySchedule, HolidaySchedule holidaySchedule) {
        Set<String> totalScheduleWorker = new HashSet<>();
        totalScheduleWorker.addAll(weekDaySchedule.getSchedule());
        totalScheduleWorker.addAll(holidaySchedule.getSchedule());
        if (totalScheduleWorker.size() > 35 || totalScheduleWorker.size() < 5) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }

    private static HolidaySchedule getHolidaySchedule() {
        while(true) {
            try {
                String holidayWorkScheduleInput = readHolidayScheduleInput();
                HolidaySchedule holidaySchedule = new HolidaySchedule(holidayWorkScheduleInput);
                return holidaySchedule;
            } catch(IllegalArgumentException e ){
                System.out.println(e.getMessage());
            }
        }
    }

    private static WeekDaySchedule getWeekDaySchedule() {
        while(true) {
            try {
                String weekWorkScheduleInput = readWeekScheduleInput();
                WeekDaySchedule weekDaySchedule = new WeekDaySchedule(weekWorkScheduleInput);
                return weekDaySchedule;
            } catch(IllegalArgumentException e ){
                System.out.println(e.getMessage());
            }
        }
    }


    private static Calendar getCalendar() {
        while (true) {
            try {
                String monthInput = readMonthInput();

                StringTokenizer st = new StringTokenizer(monthInput, ",");  // 예외 처리 해야 함
                Integer month = Integer.parseInt(st.nextToken());
                String firstDayOfWeek = st.nextToken();
                Calendar calendar = new Calendar(month, firstDayOfWeek);
                return calendar;
            } catch(IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
