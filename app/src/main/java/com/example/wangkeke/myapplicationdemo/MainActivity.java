package com.example.wangkeke.myapplicationdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder myBinder = (MyService.MyBinder) service;
            myBinder.showTip("my name is Lily!");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        findViewById(R.id.startServiceBtn).setOnClickListener(this);
        findViewById(R.id.stopServiceBtn).setOnClickListener(this);
        findViewById(R.id.bindServiceBtn).setOnClickListener(this);
        findViewById(R.id.unBindServiceBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.startServiceBtn:
                Intent intent = new Intent(MainActivity.this,MyService.class);

                startService(intent);
                break;

            case R.id.stopServiceBtn:
                Intent intentStop = new Intent(MainActivity.this,MyService.class);
                stopService(intentStop);
                break;

            case R.id.bindServiceBtn:
                Intent intentBind = new Intent(MainActivity.this,MyService.class);
                bindService(intentBind,serviceConnection,BIND_AUTO_CREATE);
                break;

            case R.id.unBindServiceBtn:
                unbindService(serviceConnection);
                break;
        }
    }
}
