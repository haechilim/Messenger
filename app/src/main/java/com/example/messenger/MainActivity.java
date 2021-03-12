package com.example.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.messenger.adepter.ChattingAdapter;
import com.example.messenger.helper.Constants;
import com.example.messenger.service.MessageService;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ChattingAdapter chattingAdapter;
    private MessageService messageService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageService = new MessageService(this);
        chattingAdapter = new ChattingAdapter(this, messageService);

        ListView listView = findViewById(R.id.chattingList);
        listView.setAdapter(chattingAdapter);

        Calendar calendar = Calendar.getInstance();

//        messageService.add("123","[web발신]\n상품이", calendar.getTimeInMillis(), false);
//        messageService.add("010-3400-2222","나중에 보자", calendar.getTimeInMillis(), false);
//        messageService.add("123989","틀니네요", calendar.getTimeInMillis(), false);

        chattingAdapter.updateList();
        chattingAdapter.notifyDataSetChanged();

        findViewById(R.id.editButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editModeSettingUi(true);
            }
        });

        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(Constants.KEY_IS_NEW_CHATTING, true);
                startChatWindowActivity(intent);
            }
        });

        findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editModeSettingUi(false);
            }
        });

        findViewById(R.id.readAll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chattingAdapter.readChattingAll();
                chattingAdapter.notifyDataSetChanged();
                editModeSettingUi(false);
            }
        });

        findViewById(R.id.read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chattingAdapter.readChatting();
                chattingAdapter.notifyDataSetChanged();
                editModeSettingUi(false);
            }
        });

        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chattingAdapter.remove();
                chattingAdapter.updateList();
                chattingAdapter.notifyDataSetChanged();
                editModeSettingUi(false);
            }
        });
    }

    public void startChatWindowActivity(Intent intent) {
        intent.setClass(this, ChatWindow.class);
        startActivityForResult(intent, 100);
    }

    public void updateEditBar(List<Chatting> list) {
        showReadAllButton(true);
        showReadButton(false);
        enabledDeleteButton(false);
        enabledReadButton(false);

        for(int i = 0; i < list.size(); i++) {
            Chatting chatting = list.get(i);

            if(chatting.isChecked()) {
                showReadAllButton(false);
                showReadButton(true);
                enabledDeleteButton(true);

                if(!chatting.isRead()) enabledReadButton(true);
            }
        }
    }

    public void editModeSettingUi(boolean editMode) {
        if(!editMode) {
            showReadAllButton(true);
            showReadButton(false);
            enabledDeleteButton(false);
            enabledReadButton(false);
        }

        showEditButton(!editMode);
        showCancelButton(editMode);
        showAddButton(!editMode);
        showEditBar(editMode);
        chattingAdapter.setEditMode(editMode);
        chattingAdapter.notifyDataSetChanged();
    }

    private void enabledDeleteButton(boolean enabled) {
        Button button = findViewById(R.id.delete);
        int color = enabled ? Color.rgb(0x2E, 0x9A, 0xFE) : Color.rgb(0xAE, 0xAE, 0xAE);

        button.setEnabled(enabled);
        button.setTextColor(color);
    }

    private void enabledReadButton(boolean enabled) {
        Button button = findViewById(R.id.read);
        int color = enabled ? Color.rgb(0x2E, 0x9A, 0xFE) : Color.rgb(0xAE, 0xAE, 0xAE);

        button.setEnabled(enabled);
        button.setTextColor(color);
    }

    private void showReadAllButton(boolean visibility) {
        findViewById(R.id.readAll).setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    private void showReadButton(boolean visibility) {
        findViewById(R.id.read).setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    private void showEditButton(boolean visibility) {
        findViewById(R.id.editButton).setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    private void showCancelButton(boolean visibility) {
        findViewById(R.id.cancelButton).setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    private void showAddButton(boolean visibility) {
        findViewById(R.id.addButton).setVisibility(visibility ? View.VISIBLE : View.INVISIBLE);
    }

    private void showEditBar(boolean visibility) {
        findViewById(R.id.editBar).setVisibility(visibility ? View.VISIBLE : View.INVISIBLE);
    }
}