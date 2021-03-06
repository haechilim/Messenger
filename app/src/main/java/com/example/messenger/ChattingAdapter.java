package com.example.messenger;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChattingAdapter extends BaseAdapter {
    private Context context;
    private static List<Chatting> list = new ArrayList<>();

    public ChattingAdapter(Context context) {
        this.context = context;
    }

    public void add(Chatting chatting) {
        list.add(chatting);
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

        View view = layoutInflater.inflate(R.layout.layout_list_item, parent, false);

        ((TextView)view.findViewById(R.id.name)).setText(list.get(position).getName());
        ((TextView)view.findViewById(R.id.lastTime)).setText(list.get(position).getLastTime());
        ((TextView)view.findViewById(R.id.message)).setText(list.get(position).getMessege());

        return view;
    }
}