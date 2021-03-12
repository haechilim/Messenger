package com.example.messenger.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.messenger.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageService extends SQLiteOpenHelper {
    SQLiteDatabase database;

    public MessageService(@Nullable Context context) {
        super(context, "messageDataBase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table MessageTable(id integer primary key autoincrement not null, name char(30) not null, contents char(300) not null, time long not null, isRead integer not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void add(String name, String contents, long time, boolean isRead) {
        database = getWritableDatabase();
        database.execSQL("insert into MessageTable(name, contents, time, isRead)" +
                " values(" + name + ", '" + contents + "', '" + time + "', '" + (isRead ? 1 : 0) + "');");
        database.close();
    }

    public void delete(String name) {
        database = getWritableDatabase();
        database.execSQL("delete from MessageTable where name = " + name + ";");
        database.close();
    }

    public List<Message> getMessages(String name) {
        List<Message> messages = new ArrayList<>();

        database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from MessageTable where name = " + name + ";", null);
        while (cursor.moveToNext()) {
            messages.add(new Message(cursor.getString(1), cursor.getString(2), cursor.getLong(3), cursor.getInt(4) != 0));
        }
        database.close();

        return messages;
    }

    public List<Message> getMessages() {
        List<Message> messages = new ArrayList<>();

        database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from MessageTable;", null);
        while (cursor.moveToNext()) {
            messages.add(new Message(cursor.getString(1), cursor.getString(2), cursor.getLong(3), cursor.getInt(4) != 0));
        }
        database.close();

        return messages;
    }

    public void readMessage(String name) {
        database = this.getWritableDatabase();
        database.execSQL("update MessageTable set isRead = 1 " + "where name = " + name + ";");
        database.close();
    }
}
