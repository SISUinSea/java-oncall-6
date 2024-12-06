package oncall.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import oncall.util.CustomException;
import oncall.util.ErrorMessage;

public class Schedule {
    private List<String> schedule;

    public Schedule(String scheduleInput) {
        List<String> scheduleInputList = new ArrayList<>();
        scheduleInputList.addAll(List.of(scheduleInput.split(",")));
        validate(scheduleInputList);
        this.schedule = scheduleInputList;
    }

    private static void validate(List<String> scheduleInputList) {
        validateWorkerNameLengthIsBetween1to5(scheduleInputList);
        Set<String> duplicateTest = new HashSet<>(scheduleInputList);
        if (duplicateTest.size() != scheduleInputList.size()) {
            throw new CustomException(ErrorMessage.INVALID_INPUT);
        }
    }

    private static void validateWorkerNameLengthIsBetween1to5(List<String> scheduleInputList) {
        scheduleInputList.forEach(workerName -> {
            if (workerName.length() > 5 || workerName.isEmpty()) {
                throw new CustomException(ErrorMessage.INVALID_INPUT);
            }
        });
    }

    public List<String> getSchedule() {
        return schedule;
    }

    public Deque<String> getEnoughScheduleAsDeque() {
        List<String> result = new ArrayList<>(schedule);
        while (result.size() < 31) {
            result.addAll(schedule);
        }
        return new ArrayDeque<String>(result);
    }
}
