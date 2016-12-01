package com.example.zaeempc.fyptest1.Screens;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.zaeempc.fyptest1.Managers.Manager;
import com.example.zaeempc.fyptest1.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permissions extends AppCompatActivity {
    ListView myListView;
    ArrayList<String> myStringArray1;
    public static int ID_USERAPPS = 1;
    public static int ID_SYSTEMAPPS = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        myListView = (ListView) findViewById(R.id.listView);
        myStringArray1 = new ArrayList<String>();

    }

    public void SystemApps(View view){
        Manager manager = new Manager();
        Log.i("test", "---Launching Manager---" );
        myStringArray1 = manager.permissionManager(getPackageManager(), ID_SYSTEMAPPS);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, myStringArray1);
        myListView.setAdapter(adapter);
    }

    public void UserApps(View view) {
        Manager manager = new Manager();
        Log.i("test", "---Launching Manager---" );
        myStringArray1 = manager.permissionManager(getPackageManager(), ID_USERAPPS);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, myStringArray1);
        myListView.setAdapter(adapter);
    }

}
