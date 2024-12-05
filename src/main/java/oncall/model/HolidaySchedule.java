package oncall.model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class HolidaySchedule {
    private Deque<String> schedule = new ArrayDeque<>();

    public HolidaySchedule(String scheduleInput) {
        List<String> scheduleInputList = List.of(scheduleInput.split(","));
        new HolidaySchedule(scheduleInputList);
    }

    private HolidaySchedule(List<String> scheduleInputList) {
        schedule.addAll(scheduleInputList);
    }
}
