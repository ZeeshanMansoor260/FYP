package com.example.zaeempc.fyptest1.CriticalResources;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeeshan on 12/2/2016.
 */
public class AdaptiveSecurity {

    ArrayList<String> myStringArray1;
    String readContact = "READ_CONTACTS";
    String writeContact = "WRITE_CONTACT";
    String getAccounts = "GET_ACCOUNTS";

    boolean isUserApp(ApplicationInfo ai) {
        int mask = ApplicationInfo.FLAG_SYSTEM | ApplicationInfo.FLAG_UPDATED_SYSTEM_APP;
        return (ai.flags & mask) == 0;
    }


    public ArrayList<String> SystemApps(PackageManager packageManager){
       // myStringArray1.clear();
        Log.i("test", "---Inside System Apps---" );
        myStringArray1 = new ArrayList<String>();
        StringBuffer appNameAndPermissions=new StringBuffer();
        PackageManager pm = packageManager;
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
                            //Log.i("test", requestedPermissions[i]);
                            if(requestedPermissions[i].endsWith(readContact) || requestedPermissions[i].endsWith(writeContact) || requestedPermissions[i].endsWith(getAccounts) )
                            {
                                Log.i("test", "----inside IF-----\n");
                                myStringArray1.add("----CRITICAL RESOURCE---- \nPackage Name: " + applicationInfo.packageName + "\nPermissions: " + requestedPermissions[i]);
                            }
                            else
                            {
                                Log.i("test", "----inside ELSE-----\n");
                                myStringArray1.add("Package Name: " + applicationInfo.packageName + "\nPermissions: " + requestedPermissions[i]);
                            }
                            appNameAndPermissions.append(requestedPermissions[i] + "\n");
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

    return myStringArray1;

    }


    public ArrayList<String> UserApps(PackageManager packageManager){
        myStringArray1 = new ArrayList<String>();
        myStringArray1.clear();
        StringBuffer appNameAndPermissions=new StringBuffer();
        PackageManager pm = packageManager;
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
                            if(requestedPermissions[i].endsWith(readContact) || requestedPermissions[i].endsWith(writeContact) || requestedPermissions[i].endsWith(getAccounts) )
                            {
                                Log.i("test", "----inside IF-----\n");
                                myStringArray1.add("----CRITICAL RESOURCE---- \nPackage Name: " + applicationInfo.packageName + "\nPermissions: " + requestedPermissions[i]);
                            }
                            else
                            {
                                Log.i("test", "----inside ELSE-----\n");
                                myStringArray1.add("Package Name: " + applicationInfo.packageName + "\nPermissions: " + requestedPermissions[i]);
                            }
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
        return myStringArray1;
    }

}


