package com.example.fakeweibo;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.fakeweibo.dao.BaseDao;
import com.example.fungwahtools.activity.BaseActivity;

/**
 * Created by 区枫华 on 2017/9/11.
 */

public class InputActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private RelativeLayout relativeLayout;
    private BaseDao dao;
    private EditText editText;

    @Override
    protected int setLayoutId() {
        return R.layout.input_activity;
    }

    @Override
    protected void initView() {
        toolbar = findView(R.id.toolbar);
        relativeLayout = findView(R.id.send_rl);
        editText = findView(R.id.input_et);
        dao = new BaseDao(this);
    }

    @Override
    protected void setView() {

    }

    @Override
    protected void initListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        relativeLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_rl:
                if (dao.insert(editText.getText().toString())) {
                    Toast.makeText(this, "发送成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }
}
