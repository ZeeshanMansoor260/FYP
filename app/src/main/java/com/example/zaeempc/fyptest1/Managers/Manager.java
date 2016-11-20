package com.example.zaeempc.fyptest1.Managers;

import android.content.Context;
import android.util.Log;

import com.example.zaeempc.fyptest1.Wifi_Connection.CheckSecurity;
import com.example.zaeempc.fyptest1.Wifi_Connection.checkConnection;

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
}
