package com.example.zaeempc.fyptest1.Screens;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.zaeempc.fyptest1.R;

import java.util.ArrayList;
import java.util.List;

public class Permissions extends AppCompatActivity {
    ListView myListView;
    ArrayList<String> myStringArray1;
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


   /*     StringBuffer appNameAndPermissions=new StringBuffer();
        PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo applicationInfo : packages) {
            Log.i("test", "App: " + applicationInfo.name + " Package: " + applicationInfo.packageName);
            boolean check = isUserApp(applicationInfo);
            if(check){
                Log.i("test", "------USER APP------" + applicationInfo.packageName);
                try {
                    PackageInfo packageInfo = pm.getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS);
                    appNameAndPermissions.append(packageInfo.packageName+"*:\n");
                    //Get Permissions
                    String[] requestedPermissions = packageInfo.requestedPermissions;
                    if(requestedPermissions != null) {
                        for (int i = 0; i < requestedPermissions.length; i++) {
                            Log.i("test", requestedPermissions[i]);
                            myStringArray1.add("Package Name: " + applicationInfo.packageName + "\nPermissions: " + requestedPermissions[i]);
                            appNameAndPermissions.append(requestedPermissions[i]+"\n");
                        }
                        appNameAndPermissions.append("\n");
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else{
                Log.i("test", "------SYSTEM APP------" + applicationInfo.packageName);
            }

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, myStringArray1);
        myListView.setAdapter(adapter);*/
    }

    boolean isUserApp(ApplicationInfo ai) {
        int mask = ApplicationInfo.FLAG_SYSTEM | ApplicationInfo.FLAG_UPDATED_SYSTEM_APP;
        return (ai.flags & mask) == 0;
    }

    public void SystemApps(View view){
        myStringArray1.clear();

        StringBuffer appNameAndPermissions=new StringBuffer();
        PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo applicationInfo : packages) {
            Log.i("test", "App: " + applicationInfo.name + " Package: " + applicationInfo.packageName);
            boolean check = isUserApp(applicationInfo);
            if(!check){
                Log.i("test", "------SYSTEM APP------" + applicationInfo.packageName);
                try {
                    PackageInfo packageInfo = pm.getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS);
                    appNameAndPermissions.append(packageInfo.packageName+"*:\n");
                    //Get Permissions
                    String[] requestedPermissions = packageInfo.requestedPermissions;
                    if(requestedPermissions != null) {
                        for (int i = 0; i < requestedPermissions.length; i++) {
                            Log.i("test", requestedPermissions[i]);
                            myStringArray1.add("Package Name: " + applicationInfo.packageName + "\nPermissions: " + requestedPermissions[i]);
                            appNameAndPermissions.append(requestedPermissions[i]+"\n");
                        }
                        appNameAndPermissions.append("\n");
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else{
                Log.i("test", "------USER APP------" + applicationInfo.packageName);
            }

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, myStringArray1);
        myListView.setAdapter(adapter);
    }

    public void UserApps(View view){
        myStringArray1.clear();
        StringBuffer appNameAndPermissions=new StringBuffer();
        PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo applicationInfo : packages) {
            Log.i("test", "App: " + applicationInfo.name + " Package: " + applicationInfo.packageName);
            boolean check = isUserApp(applicationInfo);
            if(check){
                Log.i("test", "------User APP------" + applicationInfo.packageName);
                try {
                    PackageInfo packageInfo = pm.getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS);
                    appNameAndPermissions.append(packageInfo.packageName+"*:\n");
                    //Get Permissions
                    String[] requestedPermissions = packageInfo.requestedPermissions;
                    if(requestedPermissions != null) {
                        for (int i = 0; i < requestedPermissions.length; i++) {
                            Log.i("test", requestedPermissions[i]);
                            myStringArray1.add("Package Name: " + applicationInfo.packageName + "\nPermissions: " + requestedPermissions[i]);
                            appNameAndPermissions.append(requestedPermissions[i]+"\n");
                        }
                        appNameAndPermissions.append("\n");
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else{
                Log.i("test", "------System APP------" + applicationInfo.packageName);
            }

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, myStringArray1);
        myListView.setAdapter(adapter);
    }

}
