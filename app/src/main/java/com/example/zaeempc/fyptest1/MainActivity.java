package com.example.zaeempc.fyptest1;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity implements View.OnClickListener{

    public static Button bt;
    public static TextView tv;
    private static Context con;
    public static int slevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        con = this.getApplicationContext();

        tv = (TextView) findViewById(R.id.textView);

        bt = (Button)findViewById(R.id.chCon);
        bt.setOnClickListener(this);
    }

    public void checkConnection(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) { // connected to wifi
                Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                checkSecurtiy(con);
            }
            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {  // connected to the mobile provider's data plan
                Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
            }
        }
        else {  // not connected to the internet
            Toast.makeText(context, "Not connected to wifi or mobile data!", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkSecurtiy(Context context){
        //     Toast.makeText(context, "In check Security", Toast.LENGTH_SHORT).show();
        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> networkList = wifi.getScanResults();

//get current connected SSID for comparison to ScanResult
        WifiInfo wi = wifi.getConnectionInfo();
        String currentSSID = wi.getSSID();
        //       Toast.makeText(context, "Before if "+currentSSID, Toast.LENGTH_SHORT).show();
        //       Toast.makeText(context, "NEtwork list: "+networkList, Toast.LENGTH_SHORT).show();
        if (networkList != null) {
            for (ScanResult network : networkList)
            {
                //              Log.w (TAG, currentSSID+"="+network.SSID);
                //check if current connected SSID
                if (currentSSID.equalsIgnoreCase('"'+network.SSID+'"')){
                    //get capabilities of current connection
                    String Capabilities =  network.capabilities;
                    //                Log.d (TAG, network.SSID + " capabilities : " + Capabilities);
                    //               Toast.makeText(context, network.SSID + " capabilities : " + Capabilities, Toast.LENGTH_SHORT).show();
                    if (Capabilities.contains("WPA2")) {
                        //do something
                        //                 Log.w (TAG, network.SSID + " Security Type: WPA2" );
                        tv.setText(network.capabilities+"");
                        tv.setVisibility(View.VISIBLE);
                        if(Capabilities.contains("TKIP") && Capabilities.contains("AES")){
                            Toast.makeText(context, network.SSID + " Security Type: WPA2\nEncryption Type: TKIP & AES (Combination)", Toast.LENGTH_SHORT).show();
                            slevel = 3;
                            checkSecurityLevel(con,slevel);
                        }
                        else if(Capabilities.contains("TKIP")){
                            Toast.makeText(context, network.SSID + " Security Type: WPA2\nEncryption Type: TKIP", Toast.LENGTH_SHORT).show();
                            slevel = 2;
                            checkSecurityLevel(con,slevel);
                        }
                        else{
                            Toast.makeText(context, network.SSID + " Security Type: WPA2\nEncryption Type: AES", Toast.LENGTH_SHORT).show();
                            slevel = 3;
                            checkSecurityLevel(con,slevel);
                        }
                        break;
                    }
                    else if (Capabilities.contains("WPA")) {
                        //do something
                        tv.setText(network.capabilities+"");
                        tv.setVisibility(View.VISIBLE);

                        if(Capabilities.contains("TKIP")){
                            Toast.makeText(context, network.SSID + " Security Type: WPA\nEncryption Type: TKIP", Toast.LENGTH_SHORT).show();
                            slevel = 2;
                            checkSecurityLevel(con,slevel);
                        }
                        else{
                            Toast.makeText(context, network.SSID + " Security Type: WPA\nEncryption Type: AES", Toast.LENGTH_SHORT).show();
                            slevel = 3;
                            checkSecurityLevel(con,slevel);
                        }
                        break;
                    }
                    else if (Capabilities.contains("WEP")) {
                        //do something
                        tv.setText(network.capabilities+"");
                        tv.setVisibility(View.VISIBLE);
                        Toast.makeText(context, network.SSID + " Security Type: WEP", Toast.LENGTH_SHORT).show();
                        slevel = 1;
                        checkSecurityLevel(con,slevel);
                        break;
                    }
                    else{
                        tv.setText(network.capabilities+"");
                        tv.setVisibility(View.VISIBLE);
                        Toast.makeText(context, network.SSID + " Security Type: None, Open Network", Toast.LENGTH_SHORT).show();
                        slevel = 0;
                        checkSecurityLevel(con,slevel);
                        break;
                    }

                }
            }
        }
        else
            Toast.makeText(context, "Network list is null ", Toast.LENGTH_SHORT).show();
        //   Toast.makeText(context, "After if "+currentSSID, Toast.LENGTH_SHORT).show();
    }

    public void checkSecurityLevel(Context context,int level){
        if(level == 0){
            Toast.makeText(context, "You are connected to open network... It's better to disconnect now or to disallow all critical permissions!", Toast.LENGTH_SHORT).show();
        }
        else if(level == 1){
            Toast.makeText(context, "You are connected to low security network... It's better to disallow main critical permissions!", Toast.LENGTH_SHORT).show();
        }
        else if(level == 2){
            Toast.makeText(context, "You are connected to medium security network... It's better to disallow some critical permissions!", Toast.LENGTH_SHORT).show();
        }
        else if(level == 3){
            Toast.makeText(context, "You are connected to high security network... You are safe!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chCon:
                //         Toast.makeText(con, "in con"+con, Toast.LENGTH_SHORT).show();
                checkConnection(con);
                break;
        }
    }
}