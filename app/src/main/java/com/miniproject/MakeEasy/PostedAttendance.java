package com.miniproject.MakeEasy;

import java.util.ArrayList;

public class PostedAttendance {

    ArrayList<String> markedAttendance;

    public PostedAttendance() {
    }

    public PostedAttendance(ArrayList<String> markedAttendance) {
        this.markedAttendance = markedAttendance;
    }

    public ArrayList<String> getMarkedAttendance() {
        return markedAttendance;
    }

    public void setMarkedAttendance(ArrayList<String> markedAttendance) {
        this.markedAttendance = markedAttendance;
    }
}
