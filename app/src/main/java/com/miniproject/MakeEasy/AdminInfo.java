package com.miniproject.MakeEasy;

public class AdminInfo {
    String Id,Password;

    public AdminInfo() {
    }

    public AdminInfo(String id, String password) {
        Id = id;
        Password = password;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
