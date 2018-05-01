package com.example.a25bottomsheet;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn;
    CoordinatorLayout coordinatorLayout;
    BottomSheetBehavior<View> persistentBottomSheet;
    BottomSheetDialog modalBottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout = findViewById(R.id.coordinator);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);

        initPersistentBottomSheet();
    }

    @Override
    public void onClick(View v) {
        createDialog();
    }

    private void createDialog(){
        List<DataVO> list = new ArrayList<>();
        DataVO vo = new DataVO();
        vo.title = "Keep";
        vo.image = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_lab4_1, null);
        list.add(vo);

        vo = new DataVO();
        vo.title = "Inbox";
        vo.image = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_lab4_2, null);
        list.add(vo);

        vo = new DataVO();
        vo.title = "Messenger";
        vo.image = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_lab4_3, null);
        list.add(vo);

        vo = new DataVO();
        vo.title = "Google+";
        vo.image = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_lab4_4, null);
        list.add(vo);

        Lab4RecyclerViewAdapter adapter = new Lab4RecyclerViewAdapter(list);
        View view = getLayoutInflater().inflate(R.layout.lab4_modal_sheet, null);

        try {

            RecyclerView recyclerView = view.findViewById(R.id.lab4_recyclerView);              //view.findViewById 로 해야함
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);

            modalBottomSheet = new BottomSheetDialog(this);
            modalBottomSheet.setContentView(view);
            modalBottomSheet.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initPersistentBottomSheet(){
        View bottomSheet = coordinatorLayout.findViewById(R.id.bottom_sheet);
        persistentBottomSheet = BottomSheetBehavior.from(bottomSheet);
    }
}
