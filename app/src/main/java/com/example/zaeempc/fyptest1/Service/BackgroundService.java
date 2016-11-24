package com.example.zaeempc.fyptest1.Service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.zaeempc.fyptest1.Managers.Manager;
import com.example.zaeempc.fyptest1.Screens.MainActivity;

import java.security.Provider;

/**
 * Created by Zeeshan on 11/22/2016.
 */
public class BackgroundService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped
       // Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        Manager manager = new Manager();
        String message = manager.wifiManager(this);
        MainActivity.details.setText(message);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}
