package com.example.zaeempc.fyptest1.Wifi_Connection;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.view.View;
import android.widget.Toast;

import com.example.zaeempc.fyptest1.Screens.MainActivity;

import java.util.List;

/**
 * Created by Zeeshan on 11/20/2016.
 */
public class CheckSecurity {
    public static int slevel;
    public String checkSecurity(Context context){
        //     Toast.makeText(context, "In check Security", Toast.LENGTH_SHORT).show();
        String message = "";
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
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
                        MainActivity.tv.setText(network.capabilities + "");
                        MainActivity.tv.setVisibility(View.VISIBLE);
                        if(Capabilities.contains("TKIP") && Capabilities.contains("AES")){
                            Toast.makeText(context, network.SSID + " Security Type: WPA2\nEncryption Type: TKIP & AES (Combination)", Toast.LENGTH_SHORT).show();
                            slevel = 3;
                            message = checkSecurityLevel(context,slevel);
                        }
                        else if(Capabilities.contains("TKIP")){
                            Toast.makeText(context, network.SSID + " Security Type: WPA2\nEncryption Type: TKIP", Toast.LENGTH_SHORT).show();
                            slevel = 2;
                            message = checkSecurityLevel(context,slevel);
                        }
                        else{
                            Toast.makeText(context, network.SSID + " Security Type: WPA2\nEncryption Type: AES", Toast.LENGTH_SHORT).show();
                            slevel = 3;
                            message = checkSecurityLevel(context,slevel);
                        }
                        break;
                    }
                    else if (Capabilities.contains("WPA")) {
                        //do something
                        MainActivity.tv.setText(network.capabilities + "");
                        MainActivity.tv.setVisibility(View.VISIBLE);

                        if(Capabilities.contains("TKIP")){
                            Toast.makeText(context, network.SSID + " Security Type: WPA\nEncryption Type: TKIP", Toast.LENGTH_SHORT).show();
                            slevel = 2;
                            message = checkSecurityLevel(context,slevel);
                        }
                        else{
                            Toast.makeText(context, network.SSID + " Security Type: WPA\nEncryption Type: AES", Toast.LENGTH_SHORT).show();
                            slevel = 3;
                            message = checkSecurityLevel(context,slevel);
                        }
                        break;
                    }
                    else if (Capabilities.contains("WEP")) {
                        //do something
                        MainActivity.tv.setText(network.capabilities + "");
                        MainActivity.tv.setVisibility(View.VISIBLE);
                        Toast.makeText(context, network.SSID + " Security Type: WEP", Toast.LENGTH_SHORT).show();
                        slevel = 1;
                        message = checkSecurityLevel(context,slevel);
                        break;
                    }
                    else{
                        MainActivity.tv.setText(network.capabilities + "");
                        MainActivity.tv.setVisibility(View.VISIBLE);
                        Toast.makeText(context, network.SSID + " Security Type: None, Open Network", Toast.LENGTH_SHORT).show();
                        slevel = 0;
                        message = checkSecurityLevel(context,slevel);
                        break;
                    }

                }
            }
        }
        else{
            Toast.makeText(context, "Network list is null ", Toast.LENGTH_SHORT).show();
            message = "Network list is null ";
        }
        return message;
        //   Toast.makeText(context, "After if "+currentSSID, Toast.LENGTH_SHORT).show();
    }

    public String checkSecurityLevel(Context context,int level){
        if(level == 0){
            Toast.makeText(context, "You are connected to open network... It's better to disconnect now or to disallow all critical permissions!", Toast.LENGTH_LONG).show();
            return "You are connected to open network... It's better to disconnect now or to disallow all critical permissions!";
        }
        else if(level == 1){
            Toast.makeText(context, "You are connected to low security network... It's better to disallow main critical permissions!", Toast.LENGTH_LONG).show();
            return "You are connected to low network... It's better to disconnect now or to disallow all critical permissions!";
        }
        else if(level == 2){
            Toast.makeText(context, "You are connected to medium security network... It's better to disallow some critical permissions!", Toast.LENGTH_LONG).show();
            return "You are connected to medium network... It's better to disconnect now or to disallow all critical permissions!";
        }
        else if(level == 3){
            Toast.makeText(context, "You are connected to high security network... You are safe!", Toast.LENGTH_LONG).show();
            return "You are connected to high network... It's better to disconnect now or to disallow all critical permissions!";

        }
        return "";
    }

}
