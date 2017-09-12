package com.example.fakeweibo;

import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.fakeweibo.dao.BaseDao;
import com.example.fungwahtools.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity implements View.OnClickListener, Toolbar.OnMenuItemClickListener {

    private static final int LOAD_COMPLETE = 0;

    private Toolbar toolbar;
    private BaseDao dao;
    private DrawerLayout drawerLayout;
    private FloatingActionButton floatingActionButton;
    private View.OnClickListener navigationIconListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                drawerLayout.closeDrawers();
            } else {
                drawerLayout.openDrawer(Gravity.LEFT, true);
            }
        }
    };
    private ListView listView;
    private MyAdapter adapter;
    private List<ItemBean> list = new ArrayList<>();
    private List<Map<String, String>> mapList;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case LOAD_COMPLETE:
                    adapter.notifyDataSetChanged();
                    break;
            }
            return false;
        }
    });

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        dao = new BaseDao(this);

        toolbar = findView(R.id.toolbar);
        drawerLayout = findView(R.id.drawerlayout);
        listView = findView(R.id.list_view);
        floatingActionButton = findView(R.id.fab);
        adapter = new MyAdapter(list, this);

    }

    @Override
    protected void setView() {
        toolbar.inflateMenu(R.menu.main_menu);
        listView.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        toolbar.setOnMenuItemClickListener(this);
        toolbar.setNavigationOnClickListener(navigationIconListener);
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                startActivity(InputActivity.class);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {

        }
        return true;
    }

    protected void loadLocal() {
        mapList = dao.selectAll();
        list.clear();
        for (int i = 0; i < mapList.size(); i++) {
            Map<String, String> map = mapList.get(i);
            list.add(new ItemBean(map.get("time"), map.get("content")));
        }
        handler.sendEmptyMessage(LOAD_COMPLETE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLocal();
    }
}
