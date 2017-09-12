package com.example.fakeweibo;

import android.content.Context;
import android.view.View;

import com.example.fungwahtools.adapter.BaseListAdapter;

import java.util.List;

/**
 * Created by 区枫华 on 2017/9/11.
 */

public class MyAdapter extends BaseListAdapter<ViewHolder> {

    public MyAdapter(List list, Context context) {
        super(list, context);
    }

    @Override
    protected ViewHolder initViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.list_item;
    }

    @Override
    protected void setView(ViewHolder viewHolder, int position) {
        ItemBean bean = (ItemBean) list.get(position);
        viewHolder.timeTv.setText(bean.getTime());
        viewHolder.contentTv.setText(bean.getContent());
    }
}
