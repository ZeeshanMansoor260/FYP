package com.example.zaeempc.fyptest1.Service;

import android.app.Application;

/**
 * Created by Zeeshan on 11/24/2016.
 */
public class Initiator extends Application {

    private static Initiator mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized Initiator getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(NetworkChangeReceiver.ConnectivityReceiverListener listener) {
        NetworkChangeReceiver.connectivityReceiverListener = listener;
    }
}
