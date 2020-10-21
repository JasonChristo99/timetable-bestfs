package com.company;

import com.company.model.BestFirstSearch;
import com.company.model.DataLayer;
import com.company.model.State;
import com.company.util.InputReader;

public class Main {

    public static void main(String[] args) {
        DataLayer dataLayer = new DataLayer();

        // read input
        dataLayer.setRequestList(InputReader.readRequestsAndConstantsFromFile());
        dataLayer.initTeachers();
        dataLayer.initSchClasses();

        // run the best-fs algorithm
        State goalState = new BestFirstSearch().begin(dataLayer);
        System.out.println("-".repeat(800));
        goalState.print();

    }
}
