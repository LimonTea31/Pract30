package com.example.pract30;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void onClickStart(View v) {
        startService(new Intent(this, MyService2.class).putExtra("time", 7));
        startService(new Intent(this, MyService2.class).putExtra("time", 2));
        startService(new Intent(this, MyService2.class).putExtra("time", 4));
    }

}