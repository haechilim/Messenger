package com.example.messenger.adepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.messenger.Message;
import com.example.messenger.R;

import java.util.ArrayList;
import java.util.List;

public class MessageAdepter extends BaseAdapter {
    private Context context;
    private static List<Message> list = new ArrayList<>();

    public MessageAdepter(Context context) {
        this.context = context;
    }

    public void setList(List<Message> list) {
        this.list = list;
    }

    public void add(Message message) {
        list.add(message);
    }

    public void clear() {
        list.clear();
    }

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
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.layout_message_list_item, parent, false);

        ((TextView)view.findViewById(R.id.message)).setText(list.get(position).getContents());

        return view;
    }
}
