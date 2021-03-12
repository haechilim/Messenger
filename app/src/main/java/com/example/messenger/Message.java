package com.example.messenger;

public class Message {
    private String name;
    private String contents;
    private long time;
    private boolean isRead;

    public Message(String name, String contents, long time, boolean isRead) {
        this.name = name;
        this.contents = contents;
        this.time = time;
        this.isRead = isRead;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
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
