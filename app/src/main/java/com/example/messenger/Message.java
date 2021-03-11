package com.example.messenger;

public class Message {
    private String name;
    private String content;
    private long time;
    private boolean isRead;

    public Message(String name, String content, long time, boolean isRead) {
        this.name = name;
        this.content = content;
        this.time = time;
        this.isRead = isRead;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
