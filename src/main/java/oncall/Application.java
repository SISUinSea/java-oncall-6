package oncall;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import oncall.controller.MonthScheduleGenerator;
import oncall.model.Calendar;
import oncall.model.HolidaySchedule;
import oncall.model.WeekDaySchedule;
import oncall.util.Holiday;

public class Application {
    public static void main(String[] args) {
        String monthInput = Console.readLine();

        StringTokenizer st = new StringTokenizer(monthInput, ",");  // 예외 처리 해야 함
        Integer month = Integer.parseInt(st.nextToken());
        String firstDayOfWeek = st.nextToken();
        Calendar calendar = new Calendar(month, firstDayOfWeek);

        Holiday holiday = new Holiday();

        System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요>");
        String weekWorkScheduleInput = Console.readLine();
        WeekDaySchedule weekDaySchedule = new WeekDaySchedule(weekWorkScheduleInput);
        System.out.print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요>");
        String holidayWorkScheduleInput = Console.readLine();
        HolidaySchedule holidaySchedule = new HolidaySchedule(holidayWorkScheduleInput);

        List<String> result = MonthScheduleGenerator.generateMonthSchedule(calendar, weekDaySchedule.getEnoughScheduleAsDeque(),
                holidaySchedule.getEnoughScheduleAsDeque());

        result.stream().forEach(System.out::println);
    }
}
