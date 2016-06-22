package com.example.heng.hengdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heng.hengdemo.view.timeline.PinnedHeaderExpandableListView;
import com.example.heng.hengdemo.view.timeline.TimelineListViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PinnedHeaderExpandableListView.OnHeaderUpdateListener {

    private String[] views = {"view", "view1", "view2", "view1", "view2", "view1", "view2", "view1", "view2"};
    private String[] utils = {"util","util1", "util2", "util1", "util2", "util1", "util2","util1", "util2" ,"util1", "util2"};

   private  TimelineListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public List<List<String>> initData() {
        List<List<String>> groupList = new ArrayList<List<String>>();
        List<String> viewList = Arrays.asList(views);
        List<String> utilList = Arrays.asList(utils);
        groupList.add(viewList);
        groupList.add(utilList);

        return groupList;
    }

    public void initViews() {
        PinnedHeaderExpandableListView expandableListView = (PinnedHeaderExpandableListView) findViewById(R.id.listView);
        adapter = new TimelineListViewAdapter(this, initData());
        expandableListView.setAdapter(adapter);
        // 展开所有group
        for (int i = 0, count = expandableListView.getCount(); i < count; i++) {
            expandableListView.expandGroup(i);
        }
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String tag = (String) v.getTag(R.id.tv_content);
                Toast.makeText(MainActivity.this, tag, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        expandableListView.setOnHeaderUpdateListener(this);


    }


    @Override
    public View getPinnedHeader() {
        View headerView = LayoutInflater.from(this).inflate(R.layout.item_listview_head, null);
        headerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return headerView;
    }

    @Override
    public void updatePinnedHeader(View headerView, int firstVisibleGroupPos) {
        List<String> firstVisibleGroup = (List<String>) adapter.getGroup(firstVisibleGroupPos);
        TextView textView = (TextView) headerView.findViewById(R.id.tv_head);
        textView.setText(firstVisibleGroup.get(0));
    }
}
