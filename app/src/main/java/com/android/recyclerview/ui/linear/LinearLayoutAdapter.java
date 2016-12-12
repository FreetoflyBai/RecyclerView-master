package com.android.recyclerview.ui.linear;

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
 * Time       : 2016/12/12 14:55
 * QQ         : 904869397@qq.com
 */

public class LinearLayoutAdapter extends RecyclerView.Adapter<LinearLayoutAdapter.ViewHolder>{

    String[] arrays;

    public LinearLayoutAdapter(Context context){
        this.arrays=context.getResources().getStringArray(R.array.linear_item);
    }

    @Override
    public LinearLayoutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item,parent,false);
        return new LinearLayoutAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LinearLayoutAdapter.ViewHolder holder, int position) {
        holder.item.setText(position+"");
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
