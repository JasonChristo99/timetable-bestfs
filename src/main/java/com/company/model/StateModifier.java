package com.company.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StateModifier {
    Random random = new Random();

    List<State> getChildren(State state) {
        List<State> newStates = new ArrayList<>();
        for (int i = 0; i < state.timetable.length; i++) { // for each teacher
            State newState = new State(state);
//            for (int j = 0; j < state.timetable[0].length; j++) { // swap a lesson with and empty hour
//                state.timetable[i][j]
//            }
//        }
            // pick a random non-vacant position
            int nonEmptyPos = newState.nonEmptyPosMap.get(i).get(random.nextInt(newState.nonEmptyPosMap.get(i).size()));
//            System.out.println(nonEmptyPos);

            // pick a random vacant position
            int emptyPos = newState.emptyPosMap.get(i).get(random.nextInt(newState.emptyPosMap.get(i).size()));
//            System.out.println(emptyPos);

            // swap the two
            newState.swapInRowEmptyWithNonEmpty(i, emptyPos, nonEmptyPos);

            newStates.add(newState);
        }
        return newStates;
    }
}
