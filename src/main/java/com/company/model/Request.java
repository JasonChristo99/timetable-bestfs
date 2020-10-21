package com.company.model;

public class Request {
    String teacher, schClass, subject;

    public Request(String teacher, String schClass, String subject) {
        this.teacher = teacher;
        this.schClass = schClass;
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Request{" +
                "teacher='" + teacher + '\'' +
                ", schClass='" + schClass + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
