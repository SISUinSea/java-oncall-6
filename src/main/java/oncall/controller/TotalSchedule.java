package oncall.controller;

import oncall.model.Schedule;

public record TotalSchedule(Schedule weekday, Schedule holiday) {
}
