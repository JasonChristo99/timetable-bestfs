package com.company.model;

import com.company.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// calculates the cost of a timetable
// based on various constraints
public class StateEvaluator {
    public static int evaluate(State state) {
        int cost = 0;
        if (!atMostOneLessonPerClassPerHour(state))
            cost++;
        if (!atMostMaxHoursPerTeacherPerDay(state))
            cost++;
        if (!allClassesStartFirstHourEveryDay(state))
            cost++;
        if (!noFreeHoursForAllClassesEveryDay(state))
            cost++;
        if (!noFreeHoursForAllTeachersEveryDay(state))
            cost++;
        return cost;
    }

    public static boolean noFreeHoursForAllTeachersEveryDay(State state) {
        for (int i = 0; i < state.timetable.length; i++) {
            int mostRecentHourOfTeacher = -1;
            for (int j = 0; j < state.timetable[0].length; j++) {
                if (j % Constants.SCH_HRS_PDAY == 0)
                    mostRecentHourOfTeacher = -1;
                if (!state.timetable[i][j].exists()) continue;
                if (mostRecentHourOfTeacher == -1)
                    mostRecentHourOfTeacher = j;
                else if (j - mostRecentHourOfTeacher > 1)
                    return false;
                else mostRecentHourOfTeacher = j;
            }
        }
        return true;
    }

    public static boolean allClassesStartFirstHourEveryDay(State state) {
        for (int j = 0; j < state.timetable[0].length; j += 4) {
            List<String> set = new ArrayList<>();
            for (int i = 0; i < state.timetable.length; i++) {
                if (state.timetable[i][j].schClass.isEmpty()) continue;
                if (!set.contains(state.timetable[i][j].schClass))
                    set.add(state.timetable[i][0].schClass);
            }
            if (set.size() != DataLayer.schClasses.size()) return false;
        }
        return true;
    }

    public static boolean atMostMaxHoursPerTeacherPerDay(State state) {
        int hrsOfTchrThisDay = 0;
        for (int i = 0; i < state.timetable.length; i++) {
            for (int j = 0; j < state.timetable[0].length; j++) {
                if (j % Constants.SCH_HRS_PDAY == 0)
                    hrsOfTchrThisDay = 0;
                if (!state.timetable[i][j].lesson.isEmpty())
                    hrsOfTchrThisDay++;
                if (hrsOfTchrThisDay > Constants.MAX_HRS_PTCHR_PDAY)
                    return false;
            }
        }
        return true;
    }

    public static boolean atMostOneLessonPerClassPerHour(State state) {
        for (int j = 0; j < state.timetable[0].length; j++) {
            List<String> set = new ArrayList<>();
            for (int i = 0; i < state.timetable.length; i++) {
                if (state.timetable[i][j].schClass.isEmpty()) continue;
                if (!set.contains(state.timetable[i][j].schClass))
                    set.add(state.timetable[i][j].schClass);
                else return false;
            }
        }
        return true;
    }

    public static boolean noFreeHoursForAllClassesEveryDay(State state) {
        Map<String, Integer> classMostRecentLessonHour = new HashMap<>();
        resetClassMap(classMostRecentLessonHour);
        for (int j = 0; j < state.timetable[0].length; j++) {
            if (j % Constants.SCH_HRS_PDAY == 0)
                resetClassMap(classMostRecentLessonHour);
            for (int i = 0; i < state.timetable.length; i++) {
                if (!state.timetable[i][j].exists()) continue;
                if (classMostRecentLessonHour.get(state.timetable[i][j].schClass) == -1)
                    classMostRecentLessonHour.put(state.timetable[i][j].schClass, j);
                else if (j - classMostRecentLessonHour.get(state.timetable[i][j].schClass) > 1)
                    return false;
                else classMostRecentLessonHour.put(state.timetable[i][j].schClass, j);
            }
        }
        return true;
    }

    private static void resetClassMap(Map<String, Integer> classMostRecentLessonHour) {

        for (String schClass : DataLayer.schClasses) {
            classMostRecentLessonHour.put(schClass, -1);
        }
    }
}
