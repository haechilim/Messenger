package com.example.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.messenger.adepter.MessageAdepter;
import com.example.messenger.helper.Constants;
import com.example.messenger.service.MessageService;

import java.util.Calendar;

public class ChatWindow extends AppCompatActivity {
    private MessageAdepter messageAdepter;
    private MessageService messageService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        messageAdepter = new MessageAdepter(this);
        messageService = new MessageService(this);

        EditText sendMessage = findViewById(R.id.sendMessage);
        ListView listView = findViewById(R.id.messageList);
        listView.setAdapter(messageAdepter);

        Intent intent = getIntent();

        boolean isNewChatting = intent.getBooleanExtra(Constants.KEY_IS_NEW_CHATTING, false);
        String name = intent.getStringExtra(Constants.KEY_NAME);

        updateUi(isNewChatting);
        ((TextView)findViewById(R.id.name)).setText(name);

        if(!isNewChatting) messageAdepter.setList(messageService.getMessages(name));
        else messageAdepter.clear();

        messageAdepter.notifyDataSetChanged();

        listView.setSelection(messageAdepter.getCount() - 1);

        findViewById(R.id.exitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.cencelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.sendButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                messageService.add(name, sendMessage.getText().toString().trim(), calendar.getTimeInMillis(),true);
                messageAdepter.setList(messageService.getMessages(name));
                messageAdepter.notifyDataSetChanged();

                sendMessage.setText("");
            }
        });
    }

    private void updateUi(boolean isNewChatting) {
        showExitButton(!isNewChatting);
        showCencelButton(isNewChatting);
        showAddChatting(isNewChatting);
        showTextNewChatting(isNewChatting);
    }

    private void showExitButton(boolean visibility) {
        findViewById(R.id.exitButton).setVisibility(visibility ? View.VISIBLE : View.INVISIBLE);
    }

    private void showCencelButton(boolean visibility) {
        findViewById(R.id.cencelButton).setVisibility(visibility ? View.VISIBLE : View.INVISIBLE);
    }

    private void showAddChatting(boolean isNewChatting) {
        findViewById(R.id.addChatting).setVisibility(isNewChatting ? View.VISIBLE : View.GONE);
    }

    private void showTextNewChatting(boolean isNewChatting) {
        findViewById(R.id.textNewChatting).setVisibility(isNewChatting ? View.VISIBLE : View.GONE);
        findViewById(R.id.profile).setVisibility(!isNewChatting ? View.VISIBLE : View.GONE);
    }
}