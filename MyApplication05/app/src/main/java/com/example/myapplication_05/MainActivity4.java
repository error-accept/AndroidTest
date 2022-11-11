package com.example.myapplication_05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity4 extends AppCompatActivity {

    ActionMode.Callback modeCallBack = new ActionMode.Callback() {

        public boolean onPrepareActionMode(ActionMode mode, Menu menu){
            return false;
        }

        public void onDestroyActionMode(ActionMode mode) {
            mode = null;
        }

        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.setTitle("Select");
            mode.getMenuInflater().inflate(R.menu.menu, menu);
            return true;
        }

        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            System.out.println("a");
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ListView listView=findViewById(R.id.lv);
        String[] name={"One","Two","Three","Four","Five"};
        int[] image={R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};

        List<Map<String,Object>> lists=new ArrayList<>();
        for(int i=0;i<name.length;i++){
            HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            stringObjectHashMap.put("name",name[i]);
            stringObjectHashMap.put("img",image[i]);
            lists.add(stringObjectHashMap);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(MainActivity4.this,lists,R.layout.list2_item,new String[]{"name","img"},new int[]{R.id.name,R.id.img});
        listView.setAdapter(simpleAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
            }
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return true;
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                mode.setTitle("1 Select");
                inflater.inflate(R.menu.menu, menu);
                return true;
            }
            @Override
            public void onDestroyActionMode(ActionMode mode) {
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }
        });

    }




//    ListView listView = getListView();
//listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
//listView.setMultiChoiceModeListener(new MultiChoiceModeListener() {
//
//        @Override
//        public void onItemCheckedStateChanged(ActionMode mode, int position,
//        long id, boolean checked) {
//            // Here you can do something when items are selected/de-selected,
//            // such as update the title in the CAB
//        }
//
//        @Override
//        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//            // Respond to clicks on the actions in the CAB
//            switch (item.getItemId()) {
//                case R.id.menu_delete:
//                    deleteSelectedItems();
//                    mode.finish(); // Action picked, so close the CAB
//                    return true;
//                default:
//                    return false;
//            }
//        }
//
//        @Override
//        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//            // Inflate the menu for the CAB
//            MenuInflater inflater = mode.getMenuInflater();
//            inflater.inflate(R.menu.context, menu);
//            return true;
//        }
//
//        @Override
//        public void onDestroyActionMode(ActionMode mode) {
//            // Here you can make any necessary updates to the activity when
//            // the CAB is removed. By default, selected items are deselected/unchecked.
//        }
//
//        @Override
//        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//            // Here you can perform updates to the CAB due to
//            // an <code><a href="/reference/android/view/ActionMode.html#invalidate()">invalidate()</a></code> request
//            return false;
//        }
//    });
}