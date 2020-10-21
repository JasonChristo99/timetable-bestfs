package com.company.model;

import com.company.util.SimplePrinter;

import java.util.*;

public class State implements Comparable<State> {
    LessonClass[][] timetable;
    // maps that hold info about which positions
    // of the table are empty and which are non-empty
    Map<Integer, List<Integer>> emptyPosMap;
    Map<Integer, List<Integer>> nonEmptyPosMap;
    int cost = -1;

    public State(int nRows, int nCols) {
        timetable = new LessonClass[nRows][nCols];
        emptyPosMap = new HashMap<>();
        nonEmptyPosMap = new HashMap<>();
        fillMaps();
        fillTableWithEmpty();
    }

    private void fillMaps() {
        for (int i = 0; i < timetable.length; i++) {
            emptyPosMap.put(i, new ArrayList<>());
            nonEmptyPosMap.put(i, new ArrayList<>());
            for (int j = 0; j < timetable[0].length; j++)
                emptyPosMap.get(i).add(j);
        }
    }

    public State(State state) {
        this.timetable = new LessonClass[state.timetable.length][];
        for (int i = 0; i < state.timetable.length; i++) {
            this.timetable[i] = Arrays.copyOf(state.timetable[i], state.timetable[i].length);
        }

        this.emptyPosMap = hashMapDeepCopy(state.emptyPosMap);
        this.nonEmptyPosMap = hashMapDeepCopy(state.nonEmptyPosMap);
        this.cost = state.cost;
    }

    public void fillTableWithEmpty() {
        for (int i = 0; i < timetable.length; i++) {
            for (int j = 0; j < timetable[0].length; j++) {
                makeEmpty(i, j);
            }
        }
    }

    private void makeEmpty(int i, int j) {
        timetable[i][j] = new LessonClass();
    }

    public void fillPosition(int i, int j, LessonClass value) {
        timetable[i][j] = value;
        emptyPosMap.get(i).remove(Integer.valueOf(j));
        nonEmptyPosMap.get(i).add(j);
    }

    public void vacatePosition(int i, int j) {
        timetable[i][j] = new LessonClass();
        nonEmptyPosMap.get(i).remove(Integer.valueOf(j));
        emptyPosMap.get(i).add(j);
    }

    public void swapInRowEmptyWithNonEmpty(int i, int jEmpty, int jNonEmpty) {
        System.out.println("Swapping " + jEmpty + " and " + jNonEmpty + " in row " + i + "...");
        fillPosition(i, jEmpty, timetable[i][jNonEmpty]);
        vacatePosition(i, jNonEmpty);
        evaluate();
    }

    public void fillWithRequests(DataLayer dataLayer) {
        int colCnt;
        for (int i = 0; i < DataLayer.teachersOrdered.size(); i++) {
            colCnt = 0;
            for (Request request : DataLayer.teacherRequestIndex.get(DataLayer.teachersOrdered.get(i))) {
                fillPosition(i, colCnt, new LessonClass(request.subject, request.schClass));
                colCnt++;
            }
        }
        evaluate();
    }

    int coordsFlattened(int x, int y) {
        return x * timetable[0].length + y;
    }

    int[] coordsUnflattened(int flattened) {
        return new int[]{(flattened - flattened % timetable[0].length) / timetable[0].length, flattened % timetable[0].length};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        return Arrays.deepEquals(timetable, state.timetable);
    }

    public void print() {
//        System.out.println("{State}\n" + Arrays.deepToString(timetable).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
        System.out.println("*** State *** (" + hashCode() + ")");
        SimplePrinter.print2dArray(timetable);
//        System.out.println("emptyPosMap: " + emptyPosMap);
//        System.out.println("nonEmptyPosMap: " + nonEmptyPosMap);
        System.out.println("Cost: " + cost);
    }

    public void evaluate() {
        cost = StateEvaluator.evaluate(this);
    }

    @Override
    public int compareTo(State o) {
        return Integer.compare(cost, o.cost);
    }

    public static Map<Integer, List<Integer>> hashMapDeepCopy(Map<Integer, List<Integer>> original) {
        HashMap<Integer, List<Integer>> copy = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> entry : original.entrySet()) {
            copy.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        return copy;
    }
}
