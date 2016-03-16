package com.example.wangkeke.myapplicationdemo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    private final String TAG = MyService.class.getSimpleName();

    private Context mContext;

    private MyBinder myBinder = new MyBinder();

    public MyService() {
    }

    public MyService(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCreate() {
        Log.e(TAG,"onCreate()");
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG,"onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG,"onDestroy()");
        super.onDestroy();
    }

    public class MyBinder extends Binder
    {
        public void showTip(String name)
        {
            Toast.makeText(getApplicationContext(), ""+name, Toast.LENGTH_SHORT).show();

        }
    }
}
