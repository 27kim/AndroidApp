package com.example.a14thread_handler;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView startBtn;
    ImageView pauseBtn;
    TextView textView;
    Thread thread;
    Handler handler;
    Boolean isFirst = true;
    Boolean isPaused = false;
    Boolean isLoop = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.startBtn);
        pauseBtn = findViewById(R.id.pauseBtn);
        textView = findViewById(R.id.textView);

        startBtn.setOnClickListener(this);
        pauseBtn.setOnClickListener(this);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                int count = 10;
                while (isLoop) {
                    SystemClock.sleep(1000);
                    if(!isPaused) {
                        count--;
                        Message msg = new Message();
                        msg.what = 10;
                        msg.arg1 = count;
                        handler.sendMessage(msg);

                        if (count == 0) {
                            isLoop = false;
                            Message msg2 = new Message();
                            msg.what = 20;
                            msg.obj = "finished!";
                            handler.sendMessage(msg2);
                        }
                    }
                }

            }
        };
        thread = new Thread(r);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 10) {
                    Log.e("log", String.valueOf(msg.arg1));
                    textView.setText(String.valueOf(msg.arg1));
                } else if (msg.what == 20) {
                    textView.setText(String.valueOf(msg.obj));
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        if (v == startBtn) {
            if (isFirst) {
                thread.start();
                isFirst = false;
            }else {
                isPaused =false;
            }

        } else if (v == pauseBtn) {
            isPaused = !isPaused;
        }
    }
}
