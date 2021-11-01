package com.miniproject.MakeEasy;

public class AttendancePojo {

    String attendance,received;

    public AttendancePojo() {
    }

    public AttendancePojo(String attendance,String received) {
        this.attendance = attendance;
        this.received=received;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getReceived() {
        return received;
    }

    public void setReceived(String received) {
        this.received = received;
    }
}
