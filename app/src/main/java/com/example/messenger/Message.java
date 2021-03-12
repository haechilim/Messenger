package com.example.messenger;

public class Message {
    private String name;
    private String contents;
    private long time;
    private boolean isRead;
    private boolean isSendMessage;

    public Message(String name, String contents, long time, boolean isRead, boolean isSendMessage) {
        this.name = name;
        this.contents = contents;
        this.time = time;
        this.isRead = isRead;
        this.isSendMessage = isSendMessage;
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

    public boolean isSendMessage() {
        return isSendMessage;
    }

    public void setSendMessage(boolean sendMessage) {
        isSendMessage = sendMessage;
    }
}
