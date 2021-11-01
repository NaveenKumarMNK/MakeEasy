package com.miniproject.MakeEasy;

import com.google.firebase.Timestamp;

public class EventsUploadInfo {
    String Name;
    String Uri;
    String College;
    String UploadedBy;
    String Description;
    String time;
    Timestamp uploadTime;

    public EventsUploadInfo() {
    }

    public EventsUploadInfo(String name, String uri, String college, String uploadedBy, String description, String time, Timestamp uploadTime) {
        Name = name;
        Uri = uri;
        College = college;
        UploadedBy = uploadedBy;
        Description = description;
        this.time = time;
        this.uploadTime = uploadTime;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUri() {
        return Uri;
    }

    public void setUri(String uri) {
        Uri = uri;
    }

    public String getCollege() {
        return College;
    }

    public void setCollege(String college) {
        College = college;
    }

    public String getUploadedBy() {
        return UploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        UploadedBy = uploadedBy;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }
}
