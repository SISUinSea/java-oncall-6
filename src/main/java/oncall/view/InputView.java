package oncall.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static String readMonthInput() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요>");
        String result = Console.readLine();
        return result;
    }

    public static String readWeekScheduleInput() {
        System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요>");
        String weekWorkScheduleInput = Console.readLine();
        return weekWorkScheduleInput;
    }

    public static String readHolidayScheduleInput() {
        System.out.print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요>");
        String weekWorkScheduleInput = Console.readLine();
        return weekWorkScheduleInput;
    }
}
