package oncall;

import camp.nextstep.edu.missionutils.Console;
import java.util.StringTokenizer;
import oncall.model.Calendar;

public class Application {
    public static void main(String[] args) {
        String monthInput = Console.readLine();

        StringTokenizer st = new StringTokenizer(monthInput, ",");  // 예외 처리 해야 함
        Integer month = Integer.parseInt(st.nextToken());
        String firstDayOfWeek = st.nextToken();
        Calendar calendar = new Calendar(month, firstDayOfWeek);
    }
}
