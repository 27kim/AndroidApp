package com.example.a28systemmanager;

import android.app.ActivityManager;
import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import java.util.List;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        ActivityManager manager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = manager.getRunningTasks(3);
        ActivityManager.RunningTaskInfo info = list.get(0);

        if(info.topActivity.getClassName().equals("com.example.a28systemmanager.MainActivity")){
            Intent sintent = new Intent("com.example.ACTION_TO_ACTIVITY");
            sintent.putExtra("message", "This is from Service");
            sendBroadcast(sintent);
        }else{
            //MainActivity가 화면에 보이지 않는다면
            NotificationManager notiManger=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder=null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String channelId = "one-channel";
                String channelName = "My Channel One";
                String channelDescription = "My Channel One Description";
                NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
                channel.setDescription(channelDescription);

                notiManger.createNotificationChannel(channel);
                builder = new android.support.v4.app.NotificationCompat.Builder(this, channelId);

            } else {
                builder = new NotificationCompat.Builder(this);
            }

            builder.setSmallIcon(android.R.drawable.ic_notification_overlay);
            builder.setWhen(System.currentTimeMillis());
            builder.setContentTitle("Message");
            builder.setContentText("This Message is from Service");
            builder.setAutoCancel(true);
            Intent aIntent=new Intent(this, MainActivity.class);
            PendingIntent pIntent=PendingIntent.getActivity(this, 10, aIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pIntent);
            notiManger.notify(222, builder.build());
        }
    }
}
