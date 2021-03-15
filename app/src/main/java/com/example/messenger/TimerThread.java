package com.example.messenger;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.messenger.adepter.ChattingAdapter;
import com.example.messenger.adepter.MessageAdepter;
import com.example.messenger.service.MessageService;

import java.util.Calendar;
import java.util.List;

public class TimerThread extends Thread {
    Context context;
    MessageService messageService;
    ChattingAdapter chattingAdapter;


    public TimerThread(Context context, MessageService messageService, ChattingAdapter chattingAdapter) {
        this.context = context;
        this.messageService = messageService;
        this.chattingAdapter = chattingAdapter;
    }

    @Override
    public void run() {
        String[] randomMessages = { "세상의 모서리 구부정하게 커버린", "골칫거리 outsider", "걸음걸이, 옷차림 이어폰 너머 playlist" ,"음악까지 다 minor",
                "넌 모르지 떨군 고개 위", "환한 빛 조명이", "느려도 좋으니", "결국 알게 되길", "The one only, you are my celebrity", "잊지마 넌 흐린 어둠 사이",
                "왼손으로 그린 별 하나", "보이니 그 유일함이 얼마나", "아름다울지 말야", "you are my celebrity"};
        List<String> names;
        Calendar calendar;

        while (true) {
            if((int)(Math.random() * 10 + 1) == 1) {
                calendar = Calendar.getInstance();
                names = messageService.getNames();

                if(!names.isEmpty()) {
                    messageService.add(names.get((int) (Math.random() * names.size())),
                            randomMessages[(int) (Math.random() * randomMessages.length)], calendar.getTimeInMillis(), false);
                    chattingAdapter.updateList();

                    ((MainActivity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            chattingAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.d("test", e.toString());
            }
        }
    }
}
