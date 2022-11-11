package com.example.myapplication_05;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;

public class MainActivity3 extends AppCompatActivity {
        private TextView mTextView;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main3);
            mTextView=findViewById(R.id.testText);
        }
        //该方法用于创建显示Menu
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.my_menu,menu);
            return true;
        }
        //选项菜单打开以后会调用这个方法，设置menu图标显示（icon）
        @Override
        public boolean onMenuOpened(int featureId, Menu menu) {
            if (menu != null) {
                if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                    try {
                        Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                        method.setAccessible(true);
                        method.invoke(menu, true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return super.onMenuOpened(featureId, menu);
        }

        //该方法对菜单的item进行监听
        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.small:
                    mTextView.setTextSize(10);
                    break;
                case R.id.meudin:
                    mTextView.setTextSize(16);
                    break;
                case R.id.big:
                    mTextView.setTextSize(24);
                    break;
                case R.id.putong:
                    Toast.makeText(this, "点击了第普通菜单", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.blue:
                    mTextView.setTextColor(Color.BLUE);
                    break;
                case R.id.red:
                    mTextView.setTextColor(Color.RED);
                    break;
            }
            return super.onOptionsItemSelected(item);
        }
    }
