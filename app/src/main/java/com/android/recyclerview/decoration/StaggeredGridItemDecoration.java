package com.android.recyclerview.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Description: 瀑布流位置是根据高度计算，无法根据position准确计算最后一列与最后一行
 *              so 无发去除最后一行和最后一列line
 * Author     : kevin.bai
 * Time       : 2016/12/5 18:12
 * QQ         : 904869397@qq.com
 */

public class StaggeredGridItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[] {
            android.R.attr.listDivider
    };
    private Drawable mDivider;

    public StaggeredGridItemDecoration(Context context)
    {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state){

    }



    @Override
    public void getItemOffsets(Rect outRect, int itemPosition,
                               RecyclerView parent)
    {
        outRect.set(0, 0, mDivider.getIntrinsicWidth(),mDivider.getIntrinsicHeight());
    }
}
