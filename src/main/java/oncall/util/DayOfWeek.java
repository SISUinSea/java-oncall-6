package oncall.util;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DayOfWeek {
    SUNDAY("일", true),
    MONDAY("월", false),
    TUESDAY("화", false),
    WEDNESDAY("수", false),
    THURSDAY("목", false),
    FRIDAY("금", false),
    SATURDAY("토", true);

    private String name;
    private boolean isWeekend;

    DayOfWeek(String name, boolean isWeekend) {
        this.name = name;
        this.isWeekend = isWeekend;
    }

    private static final Map<String, DayOfWeek> names =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(DayOfWeek::getName, Function.identity())));

    private static final Map<Integer, DayOfWeek> ids =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(DayOfWeek::ordinal, Function.identity())));

    public static DayOfWeek getDayOfWeek(String name) {
        if (names.containsKey(name)) {
            return names.get(name);
        }
        throw new CustomException(ErrorMessage.INVALID_INPUT);
    }

    public static DayOfWeek getDayOfWeek(Integer id) {
        if (ids.containsKey(id)) {
            return ids.get(id);
        }
        throw new CustomException(ErrorMessage.INVALID_INPUT);
    }

    public String getName() {
        return name;
    }

    public boolean isWeekend() {
        return isWeekend;
    }

}
