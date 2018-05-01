package com.example.m01_11recyclerview;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String today = "2017-07-01";
    String yesterday = "2017-06-30";

    RecyclerView recyclerView;
    List<ItemVO> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor todayCursor = db.rawQuery("select * from tb_data where date = '2017-07-01'", null);
        Cursor yesterdayyCursor = db.rawQuery("select * from tb_data where date = '2017-06-30'", null);
        Cursor beforeCursor = db.rawQuery("select * from tb_data where date != '2017-06-30' and date!= '2017-07-01'", null);

        list = new ArrayList<>();

        if(todayCursor.getCount() !=0){
            HeaderItem item = new HeaderItem();
            item.headerTitle = "오늘";
            list.add(item);
            while (todayCursor.moveToNext()){
                DataItem dataItem = new DataItem();
                dataItem.name = todayCursor.getString(1);
                dataItem.date = todayCursor.getString(2);
                list.add(dataItem);
            }
        }

        if(yesterdayyCursor.getCount() !=0){
            HeaderItem item = new HeaderItem();
            item.headerTitle = "어제";
            list.add(item);
            while (yesterdayyCursor.moveToNext()){
                DataItem dataItem = new DataItem();
                dataItem.name = yesterdayyCursor.getString(1);
                dataItem.date = yesterdayyCursor.getString(2);
                list.add(dataItem);
            }
        }

        if(beforeCursor.getCount() !=0){
            HeaderItem item = new HeaderItem();
            item.headerTitle = "이전";
            list.add(item);
            while (beforeCursor.moveToNext()){
                DataItem dataItem = new DataItem();
                dataItem.name = beforeCursor.getString(1);
                dataItem.date = beforeCursor.getString(2);
                list.add(dataItem);
            }
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(list));
        recyclerView.addItemDecoration(new MyDecoration());
    }

    abstract class ItemVO{
        public  static  final int TYPE_HEADER=0;
        public  static  final int TYPE_DATA=1;

        abstract int getType();
    }

    class  HeaderItem extends ItemVO{

        String headerTitle;
        @Override
        int getType() {
            return ItemVO.TYPE_HEADER;
        }
    }

    class  DataItem extends ItemVO{

        String name;
        String date;
        @Override
        int getType() {
            return ItemVO.TYPE_DATA;
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder{
        public TextView headerView;


        public HeaderViewHolder(View itemView) {
            super(itemView);
            headerView = itemView.findViewById(R.id.mission3_item_header);
        }
    }

    private class DataViewHolder extends RecyclerView.ViewHolder{
        public TextView nameView;
        public TextView dateView;
        public ImageView personView;
        public DataViewHolder(View itemView){
            super(itemView);
            nameView = itemView.findViewById(R.id.mission3_item_name);
            dateView = itemView.findViewById(R.id.mission3_item_date);
            personView = itemView.findViewById(R.id.mission3__item_person);
        }
    }

    //RecyclerView.Adapter<RecyclerView.ViewHolder>
    //뷰 홀더 하나 일 때는 해당 타입이지만
    //두 개이기 때문에 상위 타입으로 함
    private class MyAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private final List<ItemVO> list;

        public MyAdapter(List<ItemVO> list){
            this.list = list;
        }

        //뷰홀더가 여러 개이기 때문에 아래를 통해 각 뷰홀더를 구분함
        //자동 호출되서 항목을 식별한다.
        @Override
        public int getItemViewType(int position) {
            return list.get(position).getType();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if(viewType == ItemVO.TYPE_HEADER){
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.mission3_item_header, parent, false);
                return new HeaderViewHolder(view);
            }else {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.mission3_item_data, parent, false);
                return new DataViewHolder(view);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ItemVO itemVO = list.get(position);
            if(itemVO.getType() == ItemVO.TYPE_HEADER){
                HeaderViewHolder viewHolder = (HeaderViewHolder) holder;
                HeaderItem headerItem = (HeaderItem)itemVO;
                viewHolder.headerView.setText(headerItem.headerTitle);
            }else{
                DataViewHolder viewHolder = (DataViewHolder)holder;
                DataItem dataItem = (DataItem)itemVO;
                viewHolder.nameView.setText(dataItem.name);
                viewHolder.dateView.setText(dataItem.date);

                int count = position % 5;

                if(count == 0 ){
                    ((GradientDrawable)viewHolder.personView.getBackground()).setColor(0xff009688);
                }else if(count ==1){
                    ((GradientDrawable)viewHolder.personView.getBackground()).setColor(0xff4285f4);
                }else if(count ==2){
                    ((GradientDrawable)viewHolder.personView.getBackground()).setColor(0xff039be5);
                }else if(count ==3){
                    ((GradientDrawable)viewHolder.personView.getBackground()).setColor(0xff9c27b0);
                }else if(count ==4){
                    ((GradientDrawable)viewHolder.personView.getBackground()).setColor(0xff0097a7);
                }

            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    class MyDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int index = parent.getChildAdapterPosition(view);
            ItemVO itemVO = list.get(index);
            if(itemVO.getType() == ItemVO.TYPE_DATA){
                view.setBackgroundColor(0xFFFFFFFF);
                ViewCompat.setElevation(view, 10.0f);
            }
            outRect.set(20,10,20,10);
        }
    }

}