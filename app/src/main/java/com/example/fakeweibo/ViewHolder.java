package com.example.fakeweibo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.fungwahtools.bean.ListViewHolder;

/**
 * Created by 区枫华 on 2017/9/11.
 */

public class ViewHolder extends ListViewHolder {

    public TextView timeTv;
    public TextView contentTv;

    public ViewHolder(View view) {
        timeTv = (TextView) view.findViewById(R.id.time_tv);
        contentTv = (TextView) view.findViewById(R.id.content_tv);
    }

}
