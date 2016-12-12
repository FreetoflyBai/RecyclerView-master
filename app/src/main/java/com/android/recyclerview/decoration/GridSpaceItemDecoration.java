package com.android.recyclerview.decoration;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Description: Item top 、bottom 、right 、left 都会偏移，留出相等间隙.
 *              如果只需要item bottom and right 留出间隙（正常line），
 *              请使用GridItemDecoration，并替换为空的drawable
 * Author     : kevin.bai
 * Time       : 2016/12/7 13:59
 * QQ         : 904869397@qq.com
 */

public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration{

    public static final int VERTICAL_GRID= GridLayoutManager.VERTICAL;

    public static final int HORIZONTAL_GRID= GridLayoutManager.HORIZONTAL;

    private int mSpaceSize;

    private int mOrientation;

    public GridSpaceItemDecoration(int spaceSize, int orientation){
        this.mSpaceSize=spaceSize;
        setOrientation(orientation);

    }

    private void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_GRID && orientation != VERTICAL_GRID) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }


    private boolean getFirstRaw(int spanCount,int itemPosition){
        if(mOrientation==GridLayoutManager.VERTICAL){
            if(itemPosition<spanCount){
                return true;
            }
        }else{
            if(itemPosition%spanCount==0){
                return true;
            }
        }
        return false;
    }

    private boolean getFirstColum(int spanCount,int itemPosition){
        if(mOrientation==GridLayoutManager.VERTICAL){
            if(itemPosition%spanCount==0){
                return true;
            }
        }else{
            if(itemPosition<spanCount){
                return true;
            }
        }
        return false;
    }

    /**
     * 首行top增加间距
     * 首列left增加间距
     * 其他right and bottom 增加间距
     * @param outRect
     * @param itemPosition
     * @param parent
     */
    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        GridLayoutManager gridLayoutManager= (GridLayoutManager) parent.getLayoutManager();
        int spanCount=gridLayoutManager.getSpanCount();
        outRect.set(
                getFirstColum(spanCount,itemPosition)?mSpaceSize:0,
                getFirstRaw(spanCount,itemPosition)?mSpaceSize:0,
                mSpaceSize,
                mSpaceSize);

    }
}
