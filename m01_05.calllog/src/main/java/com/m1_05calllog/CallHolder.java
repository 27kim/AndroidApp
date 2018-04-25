package com.m1_05calllog;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CallHolder {
    public TextView name;
    public TextView date;
    public ImageView icon1;
    public ImageView icon2;

    public CallHolder(View root){
        name = root.findViewById(R.id.userName);
        date = root.findViewById(R.id.userDate);
        icon1 = root.findViewById(R.id.userImage);
        icon2 = root.findViewById(R.id.imageCall);
    }
}
