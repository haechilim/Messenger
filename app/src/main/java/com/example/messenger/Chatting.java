package com.example.messenger;

public class Chatting {
    private String name;
    private String messege;
    private String lastTime;

    public Chatting(String name, String messege, String lastTime) {
        this.name = name;
        this.messege = messege;
        this.lastTime = lastTime;
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
}
