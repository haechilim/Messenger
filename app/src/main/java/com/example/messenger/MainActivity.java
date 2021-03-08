package com.example.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ChattingAdapter chattingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chattingAdapter = new ChattingAdapter(this);

        ListView listView = findViewById(R.id.chattingList);
        listView.setAdapter(chattingAdapter);

        chattingAdapter.add(new Chatting("010-3499-3068", "[web발신]\n상품이 배송되었습니다. 자...","어제", false));
        chattingAdapter.add(new Chatting("010-3499-9999", "[web발신]\n상품이 배송되었습니다. 자...","어제", false));
        chattingAdapter.add(new Chatting("010-3499-9998", "[web발신]\n상품이 배송되었습니다. 자...","어제", false));
        chattingAdapter.add(new Chatting("010-3499-9997", "[web발신]\n상품이 배송되었습니다. 자...","어제", false));

        chattingAdapter.notifyDataSetChanged();

        findViewById(R.id.editButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editModeSettingUi(true);
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
                chattingAdapter.notifyDataSetChanged();
                editModeSettingUi(false);
            }
        });
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