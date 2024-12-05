package oncall.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Holiday {
    private final static List<Set<Integer>> holiday = new ArrayList<>();

    public Holiday() {
        for (int i = 0; i < 12 + 1; i++) {
            holiday.add(new HashSet<>());
        }

        holiday.get(1).add(1);
        holiday.get(3).add(1);
        holiday.get(5).add(5);
        holiday.get(6).add(6);
        holiday.get(8).add(15);
        holiday.get(10).add(3);
        holiday.get(10).add(9);
        holiday.get(12).add(25);
    }

    public static boolean isHoliday(Integer month, Integer day) {
        return holiday.get(month).contains(day);
    }
}

/*
* 1 : 1
* 2
* 3 : 1
* 4
* 5 : 5
* 6 : 6
* 7
* 8 : 15
* 9
* 10 : 3, 9
* 11
* 12 : 25
* */
