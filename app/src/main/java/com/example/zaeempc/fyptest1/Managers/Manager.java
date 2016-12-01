package com.example.zaeempc.fyptest1.Managers;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import com.example.zaeempc.fyptest1.CriticalResources.AdaptiveSecurity;
import com.example.zaeempc.fyptest1.Screens.Permissions;
import com.example.zaeempc.fyptest1.Wifi_Connection.CheckSecurity;
import com.example.zaeempc.fyptest1.Wifi_Connection.checkConnection;

import java.util.ArrayList;

/**
 * Created by Zeeshan on 11/20/2016.
 */
public class Manager {
    public String wifiManager(Context context){
        String message = "";
        checkConnection checkconnection = new checkConnection();
        int status = checkconnection.checkConnection(context);
        if(status == 2){
            Log.i("Status","----launch security manager!---");
            CheckSecurity checkSecurity = new CheckSecurity();
            message = checkSecurity.checkSecurity(context);
        }
        return message;
    }

    public ArrayList<String> permissionManager(PackageManager packageManager, int id){
        Log.i("test", "---Launching Adaptive Security---" );
        AdaptiveSecurity adaptiveSecurity = new AdaptiveSecurity();
        if(id == Permissions.ID_SYSTEMAPPS)
            return adaptiveSecurity.SystemApps(packageManager);
        else if(id == Permissions.ID_USERAPPS)
            return adaptiveSecurity.UserApps(packageManager);
        return null;
    }
}
