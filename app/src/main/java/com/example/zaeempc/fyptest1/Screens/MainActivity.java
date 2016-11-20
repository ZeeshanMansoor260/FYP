package com.example.zaeempc.fyptest1.Screens;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zaeempc.fyptest1.Managers.Manager;
import com.example.zaeempc.fyptest1.R;

public class MainActivity extends Activity implements View.OnClickListener{

    public static Button bt;
    public static TextView tv,details;
    private static Context con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        con = this.getApplicationContext();

        tv = (TextView) findViewById(R.id.textView);
        details = (TextView) findViewById(R.id.textView4);

        bt = (Button)findViewById(R.id.chCon);
        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chCon:
                //         Toast.makeText(con, "in con"+con, Toast.LENGTH_SHORT).show();
                //checkConnection(con);
                Manager manager = new Manager();
                String message = manager.wifiManager(con);
                details.setText(message);
                break;
        }
    }
}