package com.water.uisample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {
    private Context _context;
    private NotificationManager _manager;
    private Notification _notification;
    private Bitmap _LargeIcon = null;
    private static final int NOTIFYID_FLAG = 0;
    private Button btnShow;
    private Button btnClose;
    private Uri _sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        _context = NotificationActivity.this;
        //创建大图标的Bitmap
        _LargeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_fruit_caomei);
        _manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        _sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.biaobiao);
        bindView();
    }

    private void bindView() {
        btnShow = (Button) findViewById(R.id.btnShow);
        btnClose = (Button) findViewById(R.id.btnClose);
        btnShow.setOnClickListener(this);
        btnClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnShow:
                showNotify();
                break;

            case R.id.btnClose:
                //除了可以根据ID来取消Notification外,还可以调用cancelAll();关闭该应用产生的所有通知
                _manager.cancelAll();                          //取消Notification
                break;

        }
    }

    private void showNotify(){
        //定义一个PendingIntent点击Notification后启动一个Activity
        Intent it = new Intent(_context, WebViewActivity.class);
        PendingIntent pit = PendingIntent.getActivity(_context, 0, it, 0);

        if (Build.VERSION.SDK_INT >= 26) {
            String id = "channel_1";
            String description = "143";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel channel = new NotificationChannel(id, description, importance);
            channel.enableLights(true);
            channel.enableVibration(true);
            _manager.createNotificationChannel(channel);
            //设置图片,通知标题,发送时间,提示方式等属性
            _notification = new Notification.Builder(NotificationActivity.this, id)
                    .setCategory(Notification.CATEGORY_MESSAGE)
                    .setSmallIcon(R.drawable.emo)   //设置小图标
                    .setLargeIcon(_LargeIcon)       //设置大图标
                    .setContentTitle("特朗普")                         //标题
                    .setContentText("跟中国和欧盟打贸易战，你高兴了吧!") //内容
                    .setSubText("——记住我叫中国")         //内容下面的一小段文字
                    .setTicker("收到院办发送过来的信息")   //收到信息后状态栏显示的文字信息
                    .setWhen(System.currentTimeMillis()) //设置通知时间
                    .setContentIntent(pit)
                    .setAutoCancel(true)
                    .build();
            _manager.notify(1,  _notification);
        } else {
            //设置图片,通知标题,发送时间,提示方式等属性
            _notification = new Notification.Builder(NotificationActivity.this)
                    .setCategory(Notification.CATEGORY_MESSAGE)
                    .setSmallIcon(R.drawable.emo)   //设置小图标
                    .setLargeIcon(_LargeIcon)       //设置大图标
                    .setContentTitle("特朗普")                         //标题
                    .setContentText("跟中国和欧盟打贸易战，你高兴了吧!") //内容
                    .setSubText("——记住我叫中国")         //内容下面的一小段文字
                    .setTicker("收到院办发送过来的信息")   //收到信息后状态栏显示的文字信息
                    .setWhen(System.currentTimeMillis()) //设置通知时间
                    .setContentIntent(pit)
                    .setAutoCancel(true)
                    .build();
            _manager.notify(NOTIFYID_FLAG, _notification);
        }
    }

    /**
     * 展示文本
     */
    private void showNotifyOnlyText() {
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(_LargeIcon)
                .setContentTitle("我是只有文字效果的通知")
                .setContentText("我没有铃声、震动、呼吸灯,但我就是一个通知");
        _manager.notify(1, builder.build());
    }

    /**
     * 展示有自定义铃声效果的通知
     * 补充:使用系统自带的铃声效果:Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "6");
     */
    private void showNotifyWithRing() {
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("我是伴有铃声效果的通知")
                .setContentText("美妙么?安静听~")
                //调用系统默认响铃,设置此属性后setSound()会无效
                //.setDefaults(Notification.DEFAULT_SOUND)
                //调用系统多媒体裤内的铃声
                //.setSound(Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI,"2"));
                //调用自己提供的铃声，位于 /res/values/raw 目录下
                .setSound(_sound);
        //另一种设置铃声的方法
        //Notification notify = builder.build();
        //调用系统默认铃声
        //notify.defaults = Notification.DEFAULT_SOUND;
        //调用自己提供的铃声
        //notify.sound = Uri.parse("android.resource://com.littlejie.notification/"+R.raw.sound);
        //调用系统自带的铃声
        //notify.sound = Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI,"2");
        //_manager.notify(2,notify);
        _manager.notify(2, builder.build());
    }

    /**
     * 展示有震动效果的通知,需要在AndroidManifest.xml中申请震动权限
     * <uses-permission android:name="android.permission.VIBRATE" />
     * 补充:测试震动的时候,手机的模式一定要调成铃声+震动模式,否则你是感受不到震动的
     */
    private void showNotifyWithVibrate() {
        //震动也有两种设置方法,与设置铃声一样,在此不再赘述
        long[] vibrate = new long[]{0, 500, 1000, 1500};
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("我是伴有震动效果的通知")
                .setContentText("颤抖吧,凡人~")
                //使用系统默认的震动参数,会与自定义的冲突
                //.setDefaults(Notification.DEFAULT_VIBRATE)
                //自定义震动效果
                .setVibrate(vibrate);
        //另一种设置震动的方法
        //Notification notify = builder.build();
        //调用系统默认震动
        //notify.defaults = Notification.DEFAULT_VIBRATE;
        //调用自己设置的震动
        //notify.vibrate = vibrate;
        //_manager.notify(3,notify);
        _manager.notify(3, builder.build());
    }

    /**
     * 显示带有呼吸灯效果的通知,但是不知道为什么,自己这里测试没成功
     */
    private void showNotifyWithLights() {
        final Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("我是带有呼吸灯效果的通知")
                .setContentText("一闪一闪亮晶晶~")
                //ledARGB 表示灯光颜色、 ledOnMS 亮持续时间、ledOffMS 暗的时间
                .setLights(0xFF0000, 3000, 3000);
        Notification notify = builder.build();
        //只有在设置了标志符Flags为Notification.FLAG_SHOW_LIGHTS的时候，才支持呼吸灯提醒。
        notify.flags = Notification.FLAG_SHOW_LIGHTS;
        //设置lights参数的另一种方式
        //notify.ledARGB = 0xFF0000;
        //notify.ledOnMS = 500;
        //notify.ledOffMS = 5000;
        //使用handler延迟发送通知,因为连接usb时,呼吸灯一直会亮着
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                _manager.notify(4, builder.build());
            }
        }, 10000);
    }

    /**
     * 显示带有默认铃声、震动、呼吸灯效果的通知
     * 如需实现自定义效果,请参考前面三个例子
     */
    private void showNotifyWithMixed() {
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("我是有铃声+震动+呼吸灯效果的通知")
                .setContentText("我是最棒的~")
                //等价于setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
                .setDefaults(Notification.DEFAULT_ALL);
        _manager.notify(5, builder.build());
    }

    /**
     * 通知无限循环,直到用户取消或者打开通知栏(其实触摸就可以了),效果与FLAG_ONLY_ALERT_ONCE相反
     * 注:这里没有给Notification设置PendingIntent,也就是说该通知无法响应,所以只能手动取消
     */
    private void showInsistentNotify() {
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("我是一个死循环,除非你取消或者响应")
                .setContentText("啦啦啦~")
                .setDefaults(Notification.DEFAULT_ALL);
        Notification notify = builder.build();
        notify.flags |= Notification.FLAG_INSISTENT;
        _manager.notify(6, notify);
    }

    /**
     * 通知只执行一次,与默认的效果一样
     */
    private void showAlertOnceNotify() {
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("仔细看,我就执行一遍")
                .setContentText("好了,已经一遍了~")
                .setDefaults(Notification.DEFAULT_ALL);
        Notification notify = builder.build();
        notify.flags |= Notification.FLAG_ONLY_ALERT_ONCE;
        _manager.notify(7, notify);
    }

    /**
     * 清除所有通知
     */
    private void clearNotify() {
        _manager.cancelAll();
    }
}