package com.m1_05calllog;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CallAdapter extends ArrayAdapter<CallVO> {

    Context context;
    int resId;
    ArrayList<CallVO> data;

    public CallAdapter( Context context, int resource, ArrayList<CallVO> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resId = resource;
        this.data = objects;

    }

    @Override
    public int getCount() {
        return data.size();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, null);

            CallHolder holder = new CallHolder(convertView);
            convertView.setTag(holder);
        }

        CallHolder holder = (CallHolder) convertView.getTag();

        final CallVO cv = data.get(position);

        TextView date = holder.date;
        TextView name = holder.name;
        ImageView icon1 = holder.icon1;
        ImageView icon2 = holder.icon2;

        date.setText(cv.date);
        name.setText(cv.name);

        if(cv.icon1.equalsIgnoreCase("yes")){
            icon1.setImageDrawable(context.getDrawable(R.drawable.hong));
        }else{
            icon1.setImageDrawable(context.getDrawable(R.drawable.ic_person));
        }


        icon2.setImageDrawable(context.getDrawable(R.drawable.ic_dialer));
        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+cv.phone));
                context.startActivity(intent);
            }
        });



        return convertView;
    }
}
