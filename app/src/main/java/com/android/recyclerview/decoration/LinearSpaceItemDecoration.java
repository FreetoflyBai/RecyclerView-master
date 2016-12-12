package com.android.recyclerview.decoration;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


/**
 * Description: Item top 、bottom 、right 、left 都会偏移，留出相等间隙.
 *              如果只需要item bottom and right 留出间隙（正常line），
 *              请使用LinearItemDecoration，并替换为空的drawable
 * Author     : kevin.bai
 * Time       : 2016/12/6 14:43
 * QQ         : 904869397@qq.com
 */

public class LinearSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int VERTICAL_LIST= LinearLayoutManager.VERTICAL;

    private int HORIZONTAL_LIST= LinearLayoutManager.HORIZONTAL;

    private int mSpaceSize;

    private int mOrientation;

    public LinearSpaceItemDecoration(int spaceSize, int orientation){
        this.mSpaceSize=spaceSize;
        setOrientation(orientation);
    }


    private void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        //纵向列表首行top添加间距
        if(mOrientation==VERTICAL_LIST){
            outRect.set(mSpaceSize,itemPosition==0?mSpaceSize:0, mSpaceSize,mSpaceSize);
        //横向列表首行left添加间距
        }else if(mOrientation==HORIZONTAL_LIST){
            outRect.set(itemPosition==0?mSpaceSize:0,mSpaceSize, mSpaceSize,mSpaceSize);
        }

    }

}
