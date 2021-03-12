package com.example.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.messenger.helper.Constants;

public class ChatWindow extends AppCompatActivity {
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        name = findViewById(R.id.name);

        Intent intent = getIntent();

        boolean isNewChatting = intent.getBooleanExtra(Constants.KEY_IS_NEW_CHATTING, false);
        updateUi(isNewChatting);

        if(!isNewChatting) {
            name.setText(intent.getStringExtra(Constants.KEY_NAME));
        }

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