package com.example.notificationtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    PendingIntent pi = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //构建一个通知跳转的活动界面
        Intent intent = new Intent(this, NotificationActivity.class);
        pi = PendingIntent.getActivity(this,0,intent,0);
        //设置一个按钮弹出通知
        Button sendNotice = (Button)findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.send_notice:
                NotificationChannel notificationChannel = null;
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                //或者if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    notificationChannel = new NotificationChannel("001", "channel_name", NotificationManager.IMPORTANCE_HIGH);
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                Notification notification = new NotificationCompat.Builder(MainActivity.this, "001")
                        //设置优先权限
                        .setPriority(Notification.PRIORITY_MAX)
                        //设置通知标题
                        .setContentTitle("This is content title")
                        //设置短通知内容
                        //.setContentText("This is content text")
                        //设置长通知内容
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("Learn how to build notifications, " +
                                "send and sync data, and use voice actions. " +
                                "Get the official Android IDE and developer tools to build apps for Android"))
                        //设置大图片
                        //.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.timg26)))
                        //设置通知创建时间
                        .setWhen(System.currentTimeMillis())
                        //设置通知小图标
                        .setSmallIcon(R.mipmap.ic_launcher)
                        //设置通知大图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        //设置跳转活动
                        .setContentIntent(pi)
                        //设置跳转后小图标自动消失
                        .setAutoCancel(true)
                        //设置通知时自带bgm
                        //.setSound(Uri.fromFile(new File("...文件路径")))
                        //设置通知时振动 要在AndroidManifest文件里设置权限
                        //.setVibrate(new long[0,1000,1000,1000])
                        //设置通知时闪光灯闪烁
                        //.setLights(Color.GREEN,1000,1000)
                        //设置通知的默认效果
                        //.setDefaults(NotificationCompat.DEFAULT_ALL)
                        .build();
                notificationManager.notify(1, notification);
                break;
            default:
                break;
        }
    }
}
