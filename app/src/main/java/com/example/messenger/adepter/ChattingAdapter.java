package com.example.messenger.adepter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.messenger.Chatting;
import com.example.messenger.MainActivity;
import com.example.messenger.Message;
import com.example.messenger.service.MessageService;
import com.example.messenger.R;
import com.example.messenger.helper.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ChattingAdapter extends BaseAdapter {
    private Context context;
    private MessageService messageService;
    private static List<Chatting> list = new ArrayList<>();

    public ChattingAdapter(Context context, MessageService messageService) {
        this.context = context;
        this.messageService = messageService;
    }

    public void updateList() {
        list.clear();

        List<Message> messages = messageService.getMessages();
        List<String> nameList = new ArrayList<>();

        for (int i = 0; i < messages.size(); i++) {
            String name = messages.get(i).getName();

            if (!nameList.contains(name)) {
                nameList.add(name);
                list.add(new Chatting(messageService, name, context));
            }
        }
    }

    public void add(Chatting chatting) {
        list.add(chatting);
    }

    public void setEditMode(boolean editMode) {
        for (int i = 0; i < list.size(); i++) {
            Chatting chatting = list.get(i);
            chatting.setEditMode(editMode);
            chatting.setChecked(false);
        }
    }

    public void clear() {
        list.clear();
    }

    public void remove() {
        for (int i = 0; i < list.size(); i++) {
            Chatting chatting = list.get(i);

            if (chatting.isChecked()) messageService.delete(chatting.getName());
        }
    }

    public void readChatting() {
        for (int i = 0; i < list.size(); i++) {
            Chatting chatting = list.get(i);

            if (chatting.isChecked()) messageService.readMessage(chatting.getName());
        }
    }

    public void readChattingAll() {
        for (int i = 0; i < list.size(); i++) {
            messageService.readMessage(list.get(i).getName());
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd");

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.layout_list_item, parent, false);

        ((TextView) view.findViewById(R.id.name)).setText(list.get(position).getName());
        ((TextView) view.findViewById(R.id.lastTime)).setText(simpleDateFormat.format(new Date(list.get(position).getLastTime())));
        ((TextView) view.findViewById(R.id.message)).setText(list.get(position).getLastMessageContent());
        showReadMark(view, list.get(position));
        showCheckButtonBox(view, list.get(position));

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Chatting chatting = list.get(position);
                chatting.setSingleEditMode(true);
                showSingleEdit(v, chatting);

                return true;
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chatting chatting = list.get(position);

                if (chatting.isSingleEditMode()) {
                    chatting.setSingleEditMode(false);
                    showSingleEdit(v, chatting);
                } else if (chatting.isEditMode()) {
                    if (chatting.isChecked()) chatting.setChecked(false);
                    else chatting.setChecked(true);

                    checkChatting(view, chatting);

                    ((MainActivity) context).updateEditBar(list);
                } else {
                    String name = chatting.getName();

                    Intent intent = new Intent();
                    intent.putExtra(Constants.KEY_IS_NEW_CHATTING, false);
                    intent.putExtra(Constants.KEY_NAME, name);
                    ((MainActivity) context).startChatWindowActivity(intent);

                    chatting.setRead(true);
                    messageService.readMessage(chatting.getName());
                    showReadMark(view, chatting);
                }
            }
        });

        view.findViewById(R.id.singleRead).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chatting chatting = list.get(position);

                chatting.setRead(true);
                messageService.readMessage(chatting.getName());
                showReadMark(view, chatting);

                chatting.setSingleEditMode(false);
                showSingleEdit(view, chatting);
            }
        });

        view.findViewById(R.id.singleDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chatting chatting = list.get(position);

                messageService.delete(chatting.getName());
                updateList();

                chatting.setSingleEditMode(false);
                showSingleEdit(view, chatting);

                ((MainActivity) context).editModeSettingUi(false);
            }
        });

        return view;
    }

    private void showReadMark(View view, Chatting chatting) {
        int value = View.INVISIBLE;
        List<Message> messages = messageService.getMessages(chatting.getName());

        if (chatting.isEditMode()) value = View.GONE;
        else {
            for (int i = 0; i < messages.size(); i++) {
                Message message = messages.get(i);

                if (!message.isRead()) {
                    value = View.VISIBLE;
                    break;
                }
            }
        }

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

    private void showSingleEdit(View view, Chatting chatting) {
        view.findViewById(R.id.singleEdit).setVisibility(chatting.isSingleEditMode() ? View.VISIBLE : View.GONE);
    }
}