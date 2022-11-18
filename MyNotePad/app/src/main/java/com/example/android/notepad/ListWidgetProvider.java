package com.example.android.notepad;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;



public class ListWidgetProvider extends AppWidgetProvider {

    //定义一个action，这个action要在AndroidMainfest中去定义，不然识别不到，名字是自定义的
    private static final String CLICK_ACTION = "com.barry.widgetapp.plus.CLICK";

    //onReceive不存在widget生命周期中，它是用来接收广播，通知全局的
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        //当我们点击桌面上的widget按钮（这个按钮我们在onUpdate中已经为它设置了监听），widget就会发送广播
        //这个广播我们也在onUpdate中为它设置好了意图，设置了action，在这里我们接收到对应的action并做相应处理
        if (intent.getAction().equals(CLICK_ACTION)) {
            Toast.makeText(context,"click",Toast.LENGTH_SHORT).show();
            //因为点击按钮后要对布局中的文本进行更新，所以需要创建一个远程view
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget_layout);
            //为对应的TextView设置文本
            remoteViews.setTextViewText(R.id.tv, "按钮已经点击");
            //更新widget
            appWidgetManager.updateAppWidget(new ComponentName(context,ListWidgetProvider.class),remoteViews);
        }
    }

    //当widget第一次添加到桌面的时候回调，可添加多次widget，但该方法只回调一次
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Toast.makeText(context,"创建成功",Toast.LENGTH_SHORT).show();
    }

    //当widget被初次添加或大小被改变时回调
    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }

    /**
     * 当widget更新时回调
     *
     * @param context
     * @param appWidgetManager
     * @param appWidgetIds      这个数组使用用来存储已经创建的widget的id，因为可能创建了多个widget
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        // 获取Widget的组件名
        ComponentName thisWidget = new ComponentName(context,
                ListWidgetProvider.class);

        // 创建一个RemoteView
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.widget_layout);

        // 把这个Widget绑定到RemoteViewsService
        Intent intent = new Intent(context, MyRemoteViewsService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[0]);

        // 设置适配器
        remoteViews.setRemoteAdapter(android.R.id.widget_frame, intent);

        // 设置当显示的widget_list为空显示的View
//        remoteViews.setEmptyView(R.id.widget_list, R.layout.none_data);

        // 点击列表触发事件
        Intent clickIntent = new Intent(context, ListWidgetProvider.class);
        // 设置Action，方便在onReceive中区别点击事件
        clickIntent.setData(Uri.parse(clickIntent.toUri(Intent.URI_INTENT_SCHEME)));

        PendingIntent pendingIntentTemplate = PendingIntent.getBroadcast(
                context, 0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        remoteViews.setPendingIntentTemplate(android.R.id.widget_frame,
                pendingIntentTemplate);

        // 刷新按钮
        final Intent refreshIntent = new Intent(context,
                ListWidgetProvider.class);
        refreshIntent.setAction("refresh");
        final PendingIntent refreshPendingIntent = PendingIntent.getBroadcast(
                context, 0, refreshIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        // 更新Wdiget
        appWidgetManager.updateAppWidget(thisWidget, remoteViews);

    }

    //当 widget 被删除时回调
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }
    //当最后一个widget实例被删除时回调.
    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

}
