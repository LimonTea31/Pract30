package com.example.pract30;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MyService3 extends Service {

    final String LOG_TAG = "myLogs";

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "MyService onCreate");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "MyService onDestroy");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
//        Log.d(LOG_TAG, "MyService onStartCommand");
//        readFlags(flags);
//        MyRunThere mr = new MyRunThere(startId);
//        new Thread(mr).start();
//        return START_NOT_STICKY;

//        Log.d(LOG_TAG, "MyService onStartCommand, name = "+ intent.getStringExtra("name"));
//        readFlags(flags);
//        MyRunThere mr = new MyRunThere(startId);
//        new Thread(mr).start();
//        return START_STICKY;

        Log.d(LOG_TAG, "MyService onStartCommand, name = "+ intent.getStringExtra("name"));
        readFlags(flags);
        MyRunThere mr = new MyRunThere(startId);
        new Thread(mr).start();
        return START_REDELIVER_INTENT;


    }

    public IBinder onBind(Intent arg0) {
        return null;
    }

    void readFlags(int flags) {
        if((flags&START_FLAG_REDELIVERY) == START_FLAG_REDELIVERY)
            Log.d(LOG_TAG, "START_FLAG_REDELIVERY");
        if((flags&START_FLAG_RETRY) == START_FLAG_RETRY)
            Log.d(LOG_TAG, "START_FLAG_RETRY");
    }

    class MyRunThere implements Runnable {

        int startId;

        public MyRunThere(int startId) {
            this.startId = startId;
            Log.d(LOG_TAG, "MyRun#"+ startId + " create");
        }

        public void run() {
            Log.d(LOG_TAG, "MyRun#"+ startId + " start");
            try{
                TimeUnit.SECONDS.sleep(15);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            stop();
        }

        void stop() {
            Log.d(LOG_TAG, "MyRun#"+ startId + " end, stopSelfResult("
                    + startId + ") = "+ stopSelfResult(startId));
        }
    }
}
