package oncall.util;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DayOfWeek {
    SUNDAY("일" ),
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토");

    private String name;

    DayOfWeek(String name) {
        this.name = name;
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


    public boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek.name.equals("토") || dayOfWeek.name.equals("일");
    }


}
