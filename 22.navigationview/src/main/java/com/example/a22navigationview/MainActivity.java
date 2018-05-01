package com.example.a22navigationview;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    boolean isDrawerOpened;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawer = findViewById(R.id.main_drawer);
        toggle = new ActionBarDrawerToggle(this, drawer, R.string.drawer_open, R.string.drawer_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.main_drawer_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.menu_drawer_home){
                    showToast("NavegationDrawer - menu_drawer_home");
                }else if(id ==R.id.menu_drawer_message){
                    showToast("NavegationDrawer - menu_drawer_message");
                }else if(id ==R.id.menu_drawer_add){
                    showToast("NavegationDrawer - menu_drawer_add");
                }

                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
