package com.example.myapplication_05;

import static com.example.myapplication_05.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication_05.adapters.listAdapter;
import com.example.myapplication_05.beans.bean;

public class MainActivity extends AppCompatActivity {
    int  be_selected_item = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        ListView list = findViewById(id.lv_list);
//        ListView Lview = findViewById(id.lv_list);
//        ImageView img = findViewById(id.tx_img);
//        TextView name = findViewById(id.tx_name);

        list.setAdapter(new listAdapter(this, bean.setDefaultList()));
        list.setOnItemClickListener((adapterView, view, i, l) -> {

            if(be_selected_item != i){
                adapterView.getChildAt(be_selected_item).setBackgroundColor(Color.parseColor("#ffffff"));
                view.setBackgroundColor(Color.RED);
                Toast toast = Toast.makeText(MainActivity.this, bean.text[i], Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL , 0, 0);
                TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                v.setTextColor(Color.BLACK);
                toast.show();
                //刷新被选中item的编号
                be_selected_item = i;
            }
        });

       
    }
}