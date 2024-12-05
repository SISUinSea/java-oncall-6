package oncall.model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class WeekDaySchedule {
    private Deque<String> schedule = new ArrayDeque<>();

    public WeekDaySchedule(String scheduleInput) {
        List<String> scheduleInputList = List.of(scheduleInput.split(","));
        new WeekDaySchedule(scheduleInputList);
    }

    private WeekDaySchedule(List<String> scheduleInputList) {
        schedule.addAll(scheduleInputList);
    }
}
