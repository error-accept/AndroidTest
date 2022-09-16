package com.example.myapplication_04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("This is OnCreate","Create");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d("This is OnResume","Resume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("This is OnStart","Start");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("This is OnStop","Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("This is OnDestroy","Destroy");
    }
}