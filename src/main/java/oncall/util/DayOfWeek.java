package oncall.util;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DayOfWeek {
    SUNDAY("일", 1),
    MONDAY("월", 2),
    TUESDAY("화", 3),
    WEDNESDAY("수", 4),
    THURSDAY("목", 5),
    FRIDAY("금", 6),
    SATURDAY("토", 7);

    private String name;
    private Integer id;

    DayOfWeek(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    private static final Map<String, DayOfWeek> names =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(DayOfWeek::getName, Function.identity())));

    private static final Map<Integer, DayOfWeek> ids =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(DayOfWeek::getId, Function.identity())));

    public static DayOfWeek getDayOfWeek(String name) {
        if (names.containsKey(name)) {
            return names.get(name);
        }
        throw new IllegalArgumentException();
    }

    public static DayOfWeek getDayOfWeek(Integer id) {
        if (ids.containsKey(id)) {
            return ids.get(id);
        }
        throw new IllegalArgumentException();
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public boolean isWeekend() {
        return name.equals("토") || name.equals("일");
    }


}
