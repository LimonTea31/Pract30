package com.example.pract30;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyService4 extends Service {

    final String LOG_TAG = "myLogs";
    ExecutorService es;

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "MyService4 onCreate");
        es = Executors.newFixedThreadPool(2);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "MyService4 onDestroy");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "MyService4 onStartCommand");

        int time = intent.getIntExtra(MainActivity4.PARAM_TIME, 1);
        PendingIntent pi = intent.getParcelableExtra(MainActivity4.PARAM_PINTENT);

        MyRun4 mr = new MyRun4(time, startId, pi);
        es.execute(mr);

        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent arg0) {
        return null;
    }

    class MyRun4 implements Runnable {

        int time;
        int startId;
        PendingIntent pi;

        public MyRun4(int time, int startId, PendingIntent pi) {
            this.time = time;
            this.startId = startId;
            this.pi = pi;
            Log.d(LOG_TAG, "MyRun#"+ startId + " create");
        }

        public void run() {
            Log.d(LOG_TAG, "MyRun#"+ startId + " start, time = "+ time);
            try{
                // сообщаемобстартезадачи
                pi.send(MainActivity4.STATUS_START);

                // начинаем выполнение задачи
                TimeUnit.SECONDS.sleep(time);

                // сообщаемобокончаниизадачи
                Intent intent = new Intent().putExtra(MainActivity4.PARAM_RESULT, time * 100);
                pi.send(MyService4.this, MainActivity4.STATUS_FINISH, intent);

            } catch(InterruptedException e) {
                e.printStackTrace();
            } catch(PendingIntent.CanceledException e) {
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
