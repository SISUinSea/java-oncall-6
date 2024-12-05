package oncall.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class HolidaySchedule {
    private List<String> schedule;

    public HolidaySchedule(String scheduleInput) {
        List<String> scheduleInputList = new ArrayList<>();
        scheduleInputList.addAll(List.of(scheduleInput.split(",")));
        this.schedule = scheduleInputList;
    }

    public Deque<String> getEnoughScheduleAsDeque() {
        while (schedule.size() < 31) {
            Integer size = schedule.size();
            for (int i = 0 ; i < size; i++) {
                schedule.add(schedule.get(i));
            }
        }
        return new ArrayDeque<String>(schedule);
    }
}
