package oncall;

import static oncall.view.InputView.readHolidayScheduleInput;
import static oncall.view.InputView.readMonthInput;
import static oncall.view.InputView.readWeekScheduleInput;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import oncall.controller.MonthScheduleGenerator;
import oncall.model.Calendar;
import oncall.model.Schedule;
import oncall.util.CustomException;
import oncall.util.ErrorMessage;
import oncall.util.Holiday;
import oncall.view.OutputView;

public class Application {

    public static void main(String[] args) {
        Calendar calendar = getCalendar();
        Holiday holiday = new Holiday(); // todo enum 주어진 메소드 활용 연습, 이넘으로 바꿨어도 되지 않았을까? // 정적 팩토리 메소드에 대해서도 생각해보기
        Schedule weekDaySchedule = getWeekDaySchedule();
        Schedule holidaySchedule = getHolidaySchedule();
        validateScheduleWorker(weekDaySchedule, holidaySchedule);
        List<String> result = MonthScheduleGenerator.generateMonthSchedule(calendar, weekDaySchedule.getEnoughScheduleAsDeque(),
                holidaySchedule.getEnoughScheduleAsDeque());

        OutputView.printMonthSchedule(calendar, result);
    }

    private static void validateScheduleWorker(Schedule weekDaySchedule, Schedule holidaySchedule) {
        Set<String> totalScheduleWorker = new HashSet<>();
        totalScheduleWorker.addAll(weekDaySchedule.getSchedule());
        totalScheduleWorker.addAll(holidaySchedule.getSchedule());
        if (totalScheduleWorker.size() > 35 || totalScheduleWorker.size() < 5) {
            throw new CustomException(ErrorMessage.INVALID_INPUT);
        }
    }

    private static Schedule getHolidaySchedule() {
        while(true) {
            try {
                String holidayWorkScheduleInput = readHolidayScheduleInput();
                return new Schedule(holidayWorkScheduleInput);
            } catch(CustomException e ){
                System.out.println(e.getMessage());
            }
        }
    }

    private static Schedule getWeekDaySchedule() {
        while(true) {
            try {
                String weekWorkScheduleInput = readWeekScheduleInput();
                return new Schedule(weekWorkScheduleInput);
            } catch(CustomException e ){
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
            } catch(CustomException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
