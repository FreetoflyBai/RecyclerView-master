package com.android.recyclerview.ui.staggered;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author     : kevin.bai
 * Time       : 2016/12/12 15:14
 * QQ         : 904869397@qq.com
 */

public class StaggeredGridAdapter extends RecyclerView.Adapter<StaggeredGridAdapter.ViewHolder>{

    String[] arrays;
    List<Integer> mItemSize;
    int mOrientation=-1;

    public StaggeredGridAdapter(Context context,int orientation){
        this.arrays=context.getResources().getStringArray(R.array.linear_item);
        this.mOrientation=orientation;
        this.mItemSize = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            mItemSize.add((int) (Math.random() * 300) + 100);
        }
    }

    @Override
    public StaggeredGridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(),R.layout.adapter_item_grid,null);
        return new StaggeredGridAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StaggeredGridAdapter.ViewHolder holder, int position) {
        ViewGroup.LayoutParams mLayoutParams = holder.item.getLayoutParams();
        if(mOrientation== StaggeredGridLayoutManager.VERTICAL){
            mLayoutParams.height = mItemSize.get(position);
        }else if(mOrientation== StaggeredGridLayoutManager.HORIZONTAL){
            mLayoutParams.width = mItemSize.get(position);
        }
        holder.item.setLayoutParams(mLayoutParams);
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
