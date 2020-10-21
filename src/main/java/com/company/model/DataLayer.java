package com.company.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// holds various data needed
// at runtime from other classes
public class DataLayer {
    public static List<String> teachersOrdered = new ArrayList<>();
    public static Map<String, List<Request>> teacherRequestIndex = new HashMap<>();
    public static List<Request> requestList;
    public static List<String> schClasses;

    public void initTeachers() {
        String currTchr = requestList.get(0).teacher;
        teachersOrdered.add(currTchr);
        for (Request request : requestList) {
            if (!currTchr.equals(request.teacher)) {
                currTchr = request.teacher;
                teachersOrdered.add(currTchr);
            }
//            System.out.println(teacherRequestIndex.get(currTchr)==null);
            if (teacherRequestIndex.get(currTchr) == null) {
                teacherRequestIndex.put(currTchr, new ArrayList<>());
            }
            teacherRequestIndex.get(currTchr).add(request);
        }
    }

    public void setRequestList(List<Request> requestList) {
        DataLayer.requestList = requestList;
    }

    public void setTeachersOrdered(List<String> teachersOrdered) {
        DataLayer.teachersOrdered = teachersOrdered;
    }

    public List<String> getTeachersOrdered() {
        return teachersOrdered;
    }

    public Map<String, List<Request>> getTeacherRequestIndex() {
        return teacherRequestIndex;
    }

    public List<Request> getRequestList() {
        return requestList;
    }

    public void initSchClasses() {
        schClasses = new ArrayList<>();
        for (Request request : requestList) {
            if (!schClasses.contains(request.schClass))
                schClasses.add(request.schClass);
        }
    }
}
