package com.miniproject.MakeEasy;

import com.google.firebase.Timestamp;

public class StudentInfo {
    String RollNumber,Name,Gender,Department,Batch,Id,PhoneNumber;
    Timestamp EntryDate;

    public StudentInfo() {
    }

    public StudentInfo(String rollNumber, String name, String gender, String department, String batch, String id, String phoneNumber, Timestamp entryDate) {
        RollNumber = rollNumber;
        Name = name;
        Gender = gender;
        Department = department;
        Batch = batch;
        Id = id;
        PhoneNumber = phoneNumber;
        EntryDate = entryDate;
    }

    public String getRollNumber() {
        return RollNumber;
    }

    public void setRollNumber(String rollNumber) {
        RollNumber = rollNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getBatch() {
        return Batch;
    }

    public void setBatch(String batch) {
        Batch = batch;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Timestamp getEntryDate() {
        return EntryDate;
    }

    public void setEntryDate(Timestamp entryDate) {
        EntryDate = entryDate;
    }
}
