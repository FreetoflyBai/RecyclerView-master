package com.android.recyclerview.ui.grid;

import android.content.Context;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.recyclerview.R;

/**
 * Description:
 * Author     : kevin.bai
 * Time       : 2016/12/12 15:08
 * QQ         : 904869397@qq.com
 */

public class GridLayoutAdapter extends RecyclerView.Adapter<GridLayoutAdapter.ViewHolder> {

    String[] arrays;

    public GridLayoutAdapter(Context context){
        this.arrays=context.getResources().getStringArray(R.array.linear_item);
    }

    @Override
    public GridLayoutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(),R.layout.adapter_item_grid,null);
        return new GridLayoutAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GridLayoutAdapter.ViewHolder holder, int position) {
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
