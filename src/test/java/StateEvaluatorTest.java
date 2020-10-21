import com.company.model.DataLayer;
import com.company.model.LessonClass;
import com.company.model.State;
import com.company.model.StateEvaluator;
import com.company.util.Constants;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class StateEvaluatorTest {
    @Test
    public void atMostOneLessonPerClassPerHourTest() {
        State state = new State(2, 1);
        state.fillPosition(0, 0, new LessonClass("Math", "A1"));
        state.fillPosition(1, 0, new LessonClass("Math", "B1"));
        state.print();
        Assert.assertTrue(StateEvaluator.atMostOneLessonPerClassPerHour(state));
    }

    @Test
    public void atMostMaxHoursPerTeacherPerDayTest() {
        Constants.SCH_HRS_PDAY = 4;
        Constants.MAX_HRS_PTCHR_PDAY = 2;
        State state = new State(2, 8);
        state.fillPosition(0, 1, new LessonClass("Glwssa", "A1"));
        state.fillPosition(0, 2, new LessonClass("Glwssa", "A2"));
        state.fillPosition(0, 3, new LessonClass("Glwssa", "A2"));
        state.fillPosition(0, 5, new LessonClass("Glwssa", "A1"));
        state.fillPosition(1, 0, new LessonClass("Math", "A2"));
        state.fillPosition(1, 1, new LessonClass("Math", "A2"));
        state.fillPosition(1, 4, new LessonClass("Math", "A1"));
        state.fillPosition(1, 7, new LessonClass("Math", "A1"));
        state.print();
        Assert.assertFalse(StateEvaluator.atMostMaxHoursPerTeacherPerDay(state));
    }

    @Test
    public void allClassesStartFirstHourEveryDayTest() {
        Constants.SCH_HRS_PDAY = 4;
        Constants.MAX_HRS_PTCHR_PDAY = 2;
        DataLayer.schClasses = new ArrayList<>();
        DataLayer.schClasses.add("A1");
        DataLayer.schClasses.add("A2");
        State state = new State(2, 8);
        state.fillPosition(0, 1, new LessonClass("Glwssa", "A1"));
        state.fillPosition(0, 2, new LessonClass("Glwssa", "A2"));
        state.fillPosition(0, 3, new LessonClass("Glwssa", "A2"));
        state.fillPosition(0, 5, new LessonClass("Glwssa", "A1"));
        state.fillPosition(1, 0, new LessonClass("Math", "A2"));
        state.fillPosition(1, 1, new LessonClass("Math", "A2"));
        state.fillPosition(1, 4, new LessonClass("Math", "A1"));
        state.fillPosition(1, 7, new LessonClass("Math", "A1"));
        state.print();
        Assert.assertFalse(StateEvaluator.allClassesStartFirstHourEveryDay(state));
    }

    @Test
    public void noFreeHoursForAllClassesEveryDayTest() {
        Constants.SCH_HRS_PDAY = 4;
        Constants.MAX_HRS_PTCHR_PDAY = 2;
        DataLayer.schClasses = new ArrayList<>();
        DataLayer.schClasses.add("A1");
        DataLayer.schClasses.add("A2");
        State state = new State(2, 8);
        state.fillPosition(0, 0, new LessonClass("Glwssa", "A1"));
        state.fillPosition(0, 1, new LessonClass("Glwssa", "A1"));
        state.fillPosition(0, 4, new LessonClass("Math", "A1"));
        state.fillPosition(0, 5, new LessonClass("Math", "A1"));
        state.fillPosition(1, 0, new LessonClass("Glwssa", "A2"));
        state.fillPosition(1, 1, new LessonClass("Glwssa", "A2"));
        state.fillPosition(1, 4, new LessonClass("Math", "A2"));
        state.fillPosition(1, 5, new LessonClass("Math", "A2"));
        state.print();
        Assert.assertTrue(StateEvaluator.noFreeHoursForAllClassesEveryDay(state));
    }

    @Test
    public void evaluateTest() {
        Constants.SCH_HRS_PDAY = 4;
        Constants.MAX_HRS_PTCHR_PDAY = 2;
        DataLayer.schClasses = new ArrayList<>();
        DataLayer.schClasses.add("A1");
        DataLayer.schClasses.add("A2");
        State state = new State(2, 8);
        state.fillPosition(0, 0, new LessonClass("Glwssa", "A1"));
        state.fillPosition(0, 1, new LessonClass("Glwssa", "A1"));
        state.fillPosition(0, 4, new LessonClass("Math", "A1"));
        state.fillPosition(0, 5, new LessonClass("Math", "A1"));
        state.fillPosition(1, 0, new LessonClass("Glwssa", "A2"));
        state.fillPosition(1, 1, new LessonClass("Glwssa", "A2"));
        state.fillPosition(1, 4, new LessonClass("Math", "A2"));
        state.fillPosition(1, 5, new LessonClass("Math", "A2"));
        state.print();
        Assert.assertEquals(StateEvaluator.evaluate(state), 0);
    }

}

