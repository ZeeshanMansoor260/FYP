package com.example.zaeempc.fyptest1.Wifi_Connection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by Zeeshan on 11/20/2016.
 */
public class checkConnection {

    public int checkConnection(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) { // connected to wifi
                Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                //checkSecurtiy(con);
                return 2;
            }
            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {  // connected to the mobile provider's data plan
                Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                return 1;
            }
        }
        else {  // not connected to the internet
            Toast.makeText(context, "Not connected to wifi or mobile data!", Toast.LENGTH_SHORT).show();
            return 0;
        }
        return 0;
    }
}
