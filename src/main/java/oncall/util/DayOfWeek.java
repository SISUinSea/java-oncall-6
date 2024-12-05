package oncall.util;

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
