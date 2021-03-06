package com.example.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChattingAdapter chattingAdapter = new ChattingAdapter(this);

        ListView listView = findViewById(R.id.chattingList);
        listView.setAdapter(chattingAdapter);

        chattingAdapter.add(new Chatting("010-3499-3068", "[web발신]\n상품이 배송되었습니다. 자...","어제 >"));
        chattingAdapter.add(new Chatting("010-3499-9999", "[web발신]\n상품이 배송되었습니다. 자...","어제 >"));

        chattingAdapter.notifyDataSetChanged();
    }
}