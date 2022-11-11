package com.example.myapplication_05.beans;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication_05.R;

import java.util.ArrayList;
import java.util.List;

public class bean {
     private String tx_view;
     private int im_image;

     public static  String[] text={"Lion","Tiger","Monkey","Dog","Cat","Elephant"};
     public static  int[] images={R.drawable.lion,R.drawable.tiger,R.drawable.monkey,R.drawable.dog,R.drawable.cat,R.drawable.elephant};

    public bean(String s, int image) {

        this.im_image=image;
        this.tx_view=s;
    }

    public String getTx_view() {
        return tx_view;
    }

    public void setTx_view(String tx_view) {
        this.tx_view = tx_view;
    }

    public int getIm_image() {
        return im_image;
    }

    public void setIm_image(int im_image) {
        this.im_image = im_image;
    }

    public static List<bean> setDefaultList(){
        List<bean> lb=new ArrayList<>();
        for (int i = 0; i < text.length; i++) {
            System.out.println(i);
           lb.add(new bean(text[i],images[i]));
        }
        return  lb;
    }
}
