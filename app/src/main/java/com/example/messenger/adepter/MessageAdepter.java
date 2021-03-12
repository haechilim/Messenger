package com.example.messenger.adepter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.messenger.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageAdepter extends BaseAdapter {
    List<Message> list = new ArrayList<>();

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
