package com.example.messenger;

public class Chatting {
    private String name;
    private String messege;
    private String lastTime;
    private boolean isRead;
    private boolean editMode;

    public Chatting(String name, String messege, String lastTime, boolean isRead) {
        this.name = name;
        this.messege = messege;
        this.lastTime = lastTime;
        this.isRead = isRead;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }
}