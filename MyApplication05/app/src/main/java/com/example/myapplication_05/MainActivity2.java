package com.example.myapplication_05;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
//        builder.setIcon(R.drawable.cat);
//        builder.setTitle("这是标题");
//        builder.setMessage("这是消息")
        AlertDialog alertDialog = builder.create();
        View login= View.inflate(this,R.layout.dialog_view,null);
        alertDialog.setView(login);
        alertDialog.show();
    }
}