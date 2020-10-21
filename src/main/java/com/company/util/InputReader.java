package com.company.util;

import com.company.model.Request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class InputReader {
    public static List<Request> readRequestsAndConstantsFromFile() {
        List<Request> requests = new ArrayList<>();
        try {
            List<String> allLines = Files.readAllLines(Paths.get("in/in.txt"));
//            for (String line : allLines) System.out.println(line);
            String delimeter = " ";
            for (int i = 0; i < allLines.size(); i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(allLines.get(i), delimeter);
                if (i == 0) {
                    stringTokenizer.nextToken();
                    Constants.SCH_DAYS = Integer.parseInt(stringTokenizer.nextToken());
                } else if (i == 1) {
                    stringTokenizer.nextToken();
                    Constants.SCH_HRS_PDAY = Integer.parseInt(stringTokenizer.nextToken());
                } else if (i == 2) {
                    stringTokenizer.nextToken();
                    Constants.MAX_HRS_PTCHR_PDAY = Integer.parseInt(stringTokenizer.nextToken());
                } else {
                    String teacher = stringTokenizer.nextToken();
                    String schClass = stringTokenizer.nextToken();
                    String subject = stringTokenizer.nextToken();
                    int times = Integer.parseInt(stringTokenizer.nextToken());
                    Request request = new Request(teacher, schClass, subject);
                    List<Request> list = Collections.nCopies(times, request);
                    requests.addAll(list);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return requests;
    }
}
