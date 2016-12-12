package com.android.recyclerview.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.recyclerview.R;

/**
 * Description:
 * Author     : kevin.bai
 * Time       : 2016/12/5 12:14
 * QQ         : 904869397@qq.com
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    String[] arrays;

    public MainAdapter(Context context){
        this.arrays=context.getResources().getStringArray(R.array.main_item);
    }

    /**
     * 两种方式加载布局
     * 1.可以适配布局文件外层设置宽高，底层赋值setLayoutParams
     * 2.不能适配LayoutParams
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item,parent,false);
//        View view=View.inflate(parent.getContext(),R.layout.adapter_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {
        holder.item.setText(arrays[position]);
    }

    @Override
    public int getItemCount() {
        return arrays.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView item;
        public ViewHolder(View itemView) {
            super(itemView);
            item= (TextView) itemView.findViewById(R.id.tv_item);
        }
    }
}
