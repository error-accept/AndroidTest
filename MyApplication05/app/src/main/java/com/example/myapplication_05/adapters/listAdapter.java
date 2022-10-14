package com.example.myapplication_05.adapters;

import android.content.Context;
import android.net.vcn.VcnConfig;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication_05.MainActivity;
import com.example.myapplication_05.R;
import com.example.myapplication_05.beans.bean;

import java.util.List;

public class listAdapter extends BaseAdapter {
    private Context mContext;
    private List<bean> beans;

    public listAdapter(Context mContext, List<bean> beans) {
        this.mContext = mContext;
        this.beans = beans;
    }

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public Object getItem(int i) {
        return beans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
       if(view==null){
           view=LayoutInflater.from(this.mContext).inflate(R.layout.list_item,null);
           holder=new Holder();
          holder.tv= view.findViewById(R.id.tx_name);
          holder.img= view.findViewById(R.id.tx_img);
          view.setTag(holder);
       }else {
           holder=(Holder) view.getTag();
       }
        bean Bean= beans.get(i);
        holder.tv.setText(Bean.getTx_view());
        holder.img.setImageResource(Bean.getIm_image());
        return view;

    }
    public class  Holder{
        public ImageView img;
        public TextView tv;
    }
}
