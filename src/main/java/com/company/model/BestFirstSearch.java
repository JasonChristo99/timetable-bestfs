package com.company.model;

import com.company.util.Constants;

import java.util.*;

// executes the best first search algorithm
public class BestFirstSearch {
    List<State> openList = new ArrayList<>();
    List<State> closedList = new ArrayList<>();

    public State begin(DataLayer dataLayer) {

        State initialState = new State(DataLayer.teachersOrdered.size(), Constants.SCH_DAYS * Constants.SCH_HRS_PDAY);
        initialState.fillWithRequests(dataLayer);

        State currentState = initialState;

        StateModifier stateModifier = new StateModifier();

        while (currentState.cost != 0) {
            // put current state to closed list and remove it form open list
            closedList.add(currentState);
            openList.remove(currentState);
            System.out.println("CURRENT STATE");
            currentState.print();

            // generate children of current state
            List<State> children = stateModifier.getChildren(currentState);
            System.out.println("CHILDREN (" + children.size() + ")");
            for (State child : children) child.print();

            // add children to open list
            openList.addAll(children);

            // pick the best child from the open list
            Collections.sort(openList);

            // set it as the current state
            currentState = openList.get(0);

        }
        System.out.println("GOAL REACHED");
        return currentState;
    }
}
