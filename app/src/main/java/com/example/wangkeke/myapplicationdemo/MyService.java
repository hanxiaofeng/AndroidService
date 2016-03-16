package com.example.wangkeke.myapplicationdemo;

import android.app.Notification;
import android.app.PendingIntent;
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
        Log.e(TAG, "onCreate()");

        PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);
        // 通过Notification.Builder来创建通知，注意API Level

        Notification notify2 = new Notification.Builder(this)
                .setSmallIcon(R.drawable.icon) // 设置状态栏中的小图片，尺寸一般建议在24×24，这个图片同样也是在下拉状态栏中所显示，如果在那里需要更换更大的图片，可以使用setLargeIcon(Bitmap
                        // icon)
                .setTicker("TickerText:" + "您有新短消息，请注意查收！")// 设置在status
                        // bar上显示的提示文字
                .setContentTitle("Notification Title")// 设置在下拉status
                        // bar后Activity，本例子中的NotififyMessage的TextView中显示的标题
                .setContentText("This is the notification message")// TextView中显示的详细内容
                .setContentIntent(pendingIntent2) // 关联PendingIntent
                .setNumber(1) // 在TextView的右方显示的数字，可放大图片看，在最右侧。这个number同时也起到一个序列号的左右，如果多个触发多个通知（同一ID），可以指定显示哪一个。
                .build(); // 需要注意build()是在API level
        // 16及之后增加的，在API11中可以使用getNotificatin()来代替
        notify2.flags |= Notification.FLAG_AUTO_CANCEL;


        startForeground(1, notify2);

        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy()");
        super.onDestroy();
    }

    public class MyBinder extends Binder {
        public void showTip(String name) {
            Toast.makeText(getApplicationContext(), "" + name, Toast.LENGTH_SHORT).show();

        }
    }
}
