package com.example.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChattingAdapter chattingAdapter = new ChattingAdapter(this);

        ListView listView = findViewById(R.id.chattingList);
        listView.setAdapter(chattingAdapter);

        chattingAdapter.add(new Chatting("010-3499-3068", "[web발신]\n상품이 배송되었습니다. 자...","어제", false));
        chattingAdapter.add(new Chatting("010-3499-9999", "[web발신]\n상품이 배송되었습니다. 자...","어제", false));

        chattingAdapter.notifyDataSetChanged();

        findViewById(R.id.editButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditButton(false);
                showCancelButton(true);
                showAddButton(false);
                chattingAdapter.setEditMode(true);
                chattingAdapter.notifyDataSetChanged();
            }
        });

        findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditButton(true);
                showCancelButton(false);
                showAddButton(true);
                chattingAdapter.setEditMode(false);
                chattingAdapter.notifyDataSetChanged();
            }
        });
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
}