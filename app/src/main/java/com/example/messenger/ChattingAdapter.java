package com.example.messenger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

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

    public void setEditMode(boolean editMode) {
        for(int i = 0; i < list.size(); i++) {
            Chatting chatting = list.get(i);
            chatting.setEditMode(editMode);
            chatting.setChecked(false);
        }
    }

    public void clear() {
        list.clear();
    }

    public void remove() {
        List<Chatting> chattings = new ArrayList<>();

        for(int i = 0; i < list.size(); i++) {
            Chatting chatting = list.get(i);

            if(chatting.isChecked()) chattings.add(chatting);
        }

        for(int i = 0; i < chattings.size(); i++) {
            list.remove(chattings.get(i));
        }
    }

    public void readChatting() {
        for(int i = 0; i < list.size(); i++) {
            Chatting chatting = list.get(i);

            if(chatting.isChecked()) chatting.setRead(true);
        }
    }

    public void readChattingAll() {
        for(int i = 0; i < list.size(); i++) {
            list.get(i).setRead(true);
        }
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
        showReadMark(view, list.get(position));
        showCheckButtonBox(view, list.get(position));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(position).isEditMode()) {
                    Chatting chatting = list.get(position);

                    if(chatting.isChecked()) chatting.setChecked(false);
                    else chatting.setChecked(true);

                    checkChatting(view, chatting);

                    ((MainActivity)context).updateEditBar(list);
                }
                else {
                    Chatting chatting = list.get(position);

                    chatting.setRead(true);

                    showReadMark(view, chatting);
                }
            }
        });

        return view;
    }

    private void showReadMark(View view, Chatting chatting) {
        int value;

        if(chatting.isEditMode()) value = View.GONE;
        else value = chatting.isRead() ? View.INVISIBLE : View.VISIBLE;

        view.findViewById(R.id.readMark).setVisibility(value);
    }

    private void showCheckButtonBox(View view, Chatting chatting) {
        view.findViewById(R.id.checkButtonBox).setVisibility(chatting.isEditMode() ? View.VISIBLE : View.GONE);
        view.findViewById(R.id.pointer).setVisibility(chatting.isEditMode() ? View.GONE : View.VISIBLE);
    }

    private void checkChatting(View view, Chatting chatting) {
        int layout = chatting.isChecked() ? R.drawable.layout_checked_button : R.drawable.layout_check_button;

        view.findViewById(R.id.checkButton).setBackgroundDrawable(ContextCompat.getDrawable(context, layout));
    }
}