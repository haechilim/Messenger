package com.example.messenger;

import android.content.Context;

import com.example.messenger.service.MessageService;

import java.util.List;

public class Chatting {
    private String name;
    private List<Message> messeges;
    private String lastMessageContent;
    private long lastTime;
    private boolean isRead;
    private boolean editMode;
    private boolean singleEditMode;
    private boolean isChecked;

    public Chatting(MessageService messageService, String name, Context context) {
        this.name = name;
        this.messeges = messageService.getMessages(name);

        for(int i = 0; i < messeges.size(); i++) {
            Message message = messeges.get(i);

            if(this.lastTime < message.getTime()) {
                this.lastTime = message.getTime();
                this.isRead = message.isRead();
                this.lastMessageContent = message.getContents();
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Message> getMesseges() {
        return messeges;
    }

    public void setMesseges(List<Message> messeges) {
        this.messeges = messeges;
    }

    public String getLastMessageContent() {
        return lastMessageContent;
    }

    public void setLastMessageContent(String lastMessageContent) {
        this.lastMessageContent = lastMessageContent;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
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

    public boolean isSingleEditMode() {
        return singleEditMode;
    }

    public void setSingleEditMode(boolean singleEditMode) {
        this.singleEditMode = singleEditMode;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
