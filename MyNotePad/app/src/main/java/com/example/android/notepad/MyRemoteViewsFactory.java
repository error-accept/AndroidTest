package com.example.android.notepad;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

public class MyRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final Context mContext;
    public static List<String> mList = new ArrayList<String>();
    public static List<String> titles = new ArrayList<String>();
    public static List<String> descs = new ArrayList<String>();
    public static List<String> times = new ArrayList<String>();


    /*
     * 构造函数
     */
    public MyRemoteViewsFactory(Context context, Intent intent) {

        mContext = context;
    }

    /*
     * MyRemoteViewsFactory调用时执行，这个方法执行时间超过20秒回报错。
     * 如果耗时长的任务应该在onDataSetChanged或者getViewAt中处理
     */
    @Override
    public void onCreate() {
        NotePadProvider.DatabaseHelper databaseHelper = new NotePadProvider.DatabaseHelper(mContext);
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(NotePad.Notes.TABLE_NAME);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String orderBy = NotePad.Notes.DEFAULT_SORT_ORDER;
        String[] projection = new String[]{
                NotePad.Notes._ID, // 0
                NotePad.Notes.COLUMN_NAME_TITLE,
                NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE,// 1
                NotePad.Notes.COLUMN_NAME_NOTE
        };
        Cursor c = qb.query(
                db,            // The database to query
                projection,    // The columns to return from the query
                null,     // The columns for the where clause
                null, // The values for the where clause
                null,          // don't group the rows
                null,          // don't filter by row groups
                orderBy       // The sort order
        );

        if (c.moveToFirst()) {
            do {
                @SuppressLint("Range") String title = c.getString(c.getColumnIndex(NotePad.Notes.COLUMN_NAME_TITLE));
                @SuppressLint("Range") String desc = c.getString(c.getColumnIndex(NotePad.Notes.COLUMN_NAME_NOTE));
                @SuppressLint("Range") String time = c.getString(c.getColumnIndex(NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE));
                titles.add(title);
                descs.add(desc);
                times.add(time);
            }
            while (c.moveToNext());
        }
        System.out.println(titles);

    }


    @Override
    public void onDataSetChanged() {

    }

    /*
     * 这个方法不用多说了把，这里写清理资源，释放内存的操作
     */
    @Override
    public void onDestroy() {
        mList.clear();
    }

    /*
     * 返回集合数量
     */
    @Override
    public int getCount() {
        return mList.size();
    }

    /*
     * 创建并且填充，在指定索引位置显示的View，这个和BaseAdapter的getView类似
     */
    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_list_item_tv);
        views.setTextViewText(R.id.title, titles.get(position));
        views.setTextViewText(R.id.desc, descs.get(position));
        views.setTextViewText(R.id.time, times.get(position));

        return views;
    }

    /*
     * 显示一个"加载"View。返回null的时候将使用默认的View
     */
    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    /*
     * 不同View定义的数量。默认为1（本人一直在使用默认值）
     */
    @Override
    public int getViewTypeCount() {
        return 1;
    }

    /*
     * 返回当前索引的。
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /*
     * 如果每个项提供的ID是稳定的，即她们不会在运行时改变，就返回true（没用过。。。）
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }
}