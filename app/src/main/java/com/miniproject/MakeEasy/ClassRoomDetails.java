package com.miniproject.MakeEasy;

import com.google.firebase.Timestamp;

public class ClassRoomDetails {
    String Name,Topic,Department,Subject,ClassCode;
    Timestamp time;

    public ClassRoomDetails() {
    }

    public ClassRoomDetails(String name, String topic, String department, String subject, String classCode, Timestamp time) {
        Name = name;
        Topic = topic;
        Department = department;
        Subject = subject;
        ClassCode = classCode;
        this.time = time;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getClassCode() {
        return ClassCode;
    }

    public void setClassCode(String classCode) {
        ClassCode = classCode;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
