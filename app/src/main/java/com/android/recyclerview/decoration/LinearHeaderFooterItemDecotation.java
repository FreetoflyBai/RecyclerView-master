package com.android.recyclerview.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Description:
 * Author     : kevin.bai
 * Time       : 2016/12/13 17:50
 * QQ         : 904869397@qq.com
 */

public class LinearHeaderFooterItemDecotation extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };
    private int[] HEADER_FOOTER_COUNT=new int[]{
            0,0
    };
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    private Drawable mDivider;
    private int mOrientation;

    public LinearHeaderFooterItemDecotation(Context context, int orientation ,int[] headerFooterCount) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);
        this.HEADER_FOOTER_COUNT=headerFooterCount;
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {

    }

    private boolean isLast(int itemPosition,int childCount){
        if(itemPosition<HEADER_FOOTER_COUNT[0]){
            return true;
        }else if(itemPosition>=childCount-HEADER_FOOTER_COUNT[1]){
            return true;
        }else{
            itemPosition=itemPosition-HEADER_FOOTER_COUNT[0];
            if((itemPosition+1)==childCount){
                return true;
            }
        }
        return false;
    }

    /**
     * 最后一行 bottom 不偏移
     * 最后一列 right 不偏移
     *
     * @param outRect
     * @param itemPosition
     * @param parent
     */
    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        int childCount=parent.getAdapter().getItemCount();
        if (mOrientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, isLast(itemPosition,childCount)?0:mDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, isLast(itemPosition,childCount)?0:mDivider.getIntrinsicWidth(), 0);
        }
    }
}
