package com.example.tjjunlintx.diyview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tjjunlintx.diyview.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tjjunlintx on 2017/8/18.
 */

public class SearchAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;

    public SearchAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.search_item,null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.search_textview.setText(list.get(i));
        return view;
    }
    public class ViewHolder{
        @BindView(R.id.search_textview)
        TextView search_textview;

        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}
