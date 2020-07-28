package com.antu.bazinga.antitheftdevice;

/**
 * Created by Antu on 23-May-18.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class startMonitor extends AppCompatActivity {
    private static final String SOURCE = "source";
    private static final String START_MONITOR = "start_monitor";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Toast.makeText(this,"2",Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_start_monitor);
    }

    public void goBack(View v)
    {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra(SOURCE,START_MONITOR);
        startActivity(intent);
    }
}
