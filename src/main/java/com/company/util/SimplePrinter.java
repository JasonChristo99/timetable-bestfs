package com.company.util;

import com.company.model.DataLayer;
import com.company.model.LessonClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SimplePrinter {
    public static void printList(List<?> list) {
        for (Object element : list)
            System.out.println(element);
    }

    public static void printHashMap(Map<?, ?> map) {
        for (Object key : map.keySet()) {
            if (map.get(key) instanceof ArrayList) {
                System.out.println(key + " => ");
                printList((List<?>) map.get(key));
            } else
                System.out.println(key + " => " + map.get(key));
        }
    }

    public static void print2dArray(Object[][] table) {
        for (int i = 0; i < table.length; i++) {
            System.out.print(i + ": ");
            System.out.println(Arrays.toString(table[i]));
        }
    }

    public static void print2dArray(LessonClass[][] table) {
        for (int i = 0; i < table.length; i++) {
            System.out.print(String.format("%7s : ", DataLayer.teachersOrdered.get(i)));
            for (int j = 0; j < table[0].length; j++) {
                System.out.print(String.format("%10s|", table[i][j]));
            }
            System.out.println();
        }
    }
}
