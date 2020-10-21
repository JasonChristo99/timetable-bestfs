package com.company.model;

public class LessonClass {
    String lesson, schClass;

    public LessonClass() {
        this.lesson = "";
        this.schClass = "";
    }

    public LessonClass(String lesson, String schClass) {
        this.lesson = lesson;
        this.schClass = schClass;
    }

    public String getLesson() {
        return lesson;
    }

    public String getSchClass() {
        return schClass;
    }

    @Override
    public String toString() {
        if (lesson.equals("")) return "EMPTY";
        return schClass + "·" + lesson;
    }

    public boolean exists() {
        return !schClass.isEmpty();
    }
}
