package com.example.a15thread_asynctask;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView startView;
    ImageView pauseView;
    TextView textView;

    boolean isFirst = true;

    MyAsyncTask asyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startView = findViewById(R.id.startBtn);
        pauseView = findViewById(R.id.pauseBtn);
        textView = findViewById(R.id.textView);

        startView.setOnClickListener(this);
        pauseView.setOnClickListener(this);

        asyncTask = new MyAsyncTask();
    }

    @Override
    public void onClick(View v) {
        if(v==startView){
            if(isFirst){
                asyncTask.isRun=true;
                asyncTask.execute();
                isFirst=false;
            }else {
                asyncTask.isRun=true;
            }
        }else if(v==pauseView){
            asyncTask.isRun=false;
        }
    }

    class MyAsyncTask extends AsyncTask<Void, Integer, String> {
        boolean loopFlag=true;
        boolean isRun;
        @Override
        protected void onPostExecute(String s) {
            textView.setText(s);

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            textView.setText(String.valueOf(values[0]));
        }

        @Override
        protected String doInBackground(Void... voids) {

            int count=10;
            while (loopFlag){
                SystemClock.sleep(1000);
                if(isRun){
                    count--;
                    publishProgress(count);
                    if(count==0){
                        loopFlag=false;
                    }
                }
            }
            return "Finish!!";
        }
    }
}
