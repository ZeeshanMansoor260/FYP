package com.example.zaeempc.fyptest1.Screens;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.zaeempc.fyptest1.Managers.Manager;
import com.example.zaeempc.fyptest1.R;
import com.example.zaeempc.fyptest1.Service.BackgroundService;
import com.example.zaeempc.fyptest1.Service.Initiator;
import com.example.zaeempc.fyptest1.Service.NetworkChangeReceiver;

public class MainActivity extends AppCompatActivity
        implements NetworkChangeReceiver.ConnectivityReceiverListener{

    public static Button bt;
    public static TextView tv,details;
    private static Context con;
    private static ToggleButton tb;
    boolean bool;
    int switch_state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        con = this.getApplicationContext();

        tv = (TextView) findViewById(R.id.textView);
        details = (TextView) findViewById(R.id.textView4);
        tb=(ToggleButton)findViewById(R.id.toggleButton);

        bt = (Button)findViewById(R.id.chCon);
        //bt.setOnClickListener(this);
        checkConnection();
        bool = isMyServiceRunning(BackgroundService.class);
        if(bool == true){
            tb.setChecked(true);
        }
        Log.i("Check", "Bool  :" + bool);
    }

    private void checkConnection() {
        boolean isConnected = NetworkChangeReceiver.isConnected();
        if(isConnected){
            Log.i("State","Connected!!!!!");
        }
        else{
            Log.i("State", "Not Connected!!!!!");
        }
    }

    public void ChangeState(View view){
        Log.i("Check", "Switch clicked");
        CharSequence state = tb.getText();
        if( state.equals("On")){
            Log.i("Check", "Launch Service");
            switch_state= 1;
            startService(new Intent(getBaseContext(), BackgroundService.class));
        }
        else if (state.equals("Off")){
            Log.i("Check", "Destroy Service");
            switch_state = 0;
            stopService(new Intent(getBaseContext(), BackgroundService.class));
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        // register connection status listener
        Initiator.getInstance().setConnectivityListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // register connection status listener
        Initiator.getInstance().setConnectivityListener(this);
    }

    public void ChangeScreen(View view){
        Intent intent = new Intent(MainActivity.this, Permissions.class);
        startActivity(intent);
    }
    /**
     * Callback will be triggered when there is change in
     * network connection
     */
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        Log.i("Status", "Change in state detected switch_state-->" + switch_state );
        if(switch_state == 1)
            startService(new Intent(getBaseContext(), BackgroundService.class));

    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        Log.i("Check", "Inside checking service");
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}