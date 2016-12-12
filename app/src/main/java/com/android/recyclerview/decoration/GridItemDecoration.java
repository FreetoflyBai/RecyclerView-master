package com.android.recyclerview.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

/**
 * Description:
 * Author     : kevin.bai
 * Time       : 2016/12/5 11:54
 * QQ         : 904869397@qq.com
 */
public class GridItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    private int[] HEADER_FOOTER_COUNT=new int[]{
            1,1
    };

    private Drawable mDivider;

    public GridItemDecoration(Context context) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, State state) {
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    /**
     * 获取列数
     * @param parent
     * @return
     */
    private int getSpanCount(RecyclerView parent) {
        int spanCount = -1;
        LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }

    /**
     * 绘制横向line
     * 判断不是最后一行才去绘制
     * @param c
     * @param parent
     */
    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (!isLastRaw(parent, i, getSpanCount(parent), childCount)) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int left = child.getLeft() - params.leftMargin;
                final int right = child.getRight() + params.rightMargin
                        + mDivider.getIntrinsicWidth();//添加竖向线宽
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }

        }
    }

    /**
     * 绘制纵向line
     * 判断不是最后一列才去绘制
     * @param c
     * @param parent
     */
    private void drawVertical(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (!isLastColumn(parent, i, getSpanCount(parent), childCount)) {
                final View child = parent.getChildAt(i);

                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int top = child.getTop() - params.topMargin;
                final int bottom = child.getBottom() + params.bottomMargin
                        + mDivider.getIntrinsicHeight();//添加横向线高
                final int left = child.getRight() + params.rightMargin;
                final int right = left + mDivider.getIntrinsicWidth();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }

        }
    }

    /**
     * 判断是否是最后一列
     * 横向布局与纵向布局计算方法不一样
     * @param parent
     * @param pos
     * @param spanCount
     * @param childCount
     * @return
     */
    private boolean isLastColumn(RecyclerView parent, int pos, int spanCount,
                                 int childCount) {
        if(pos<HEADER_FOOTER_COUNT[0]){
            return true;
        }else if(pos>=(childCount-HEADER_FOOTER_COUNT[0])){
            return true;
        }else{
            pos=pos-HEADER_FOOTER_COUNT[0];
            childCount=childCount-(HEADER_FOOTER_COUNT[0]+HEADER_FOOTER_COUNT[1]);
        }
        LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int orientation=((GridLayoutManager)layoutManager).getOrientation();
            if(orientation==GridLayoutManager.VERTICAL){
                if ((pos + 1) % spanCount == 0){return true;}
            }else{
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount){return true;}
            }
        }
        return false;
    }

    /**
     * 判断是否是最后一行
     * 横向布局与纵向布局计算方法不一样
     * @param parent
     * @param pos
     * @param spanCount
     * @param childCount
     * @return
     */
    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount,
                              int childCount) {
        if(pos<HEADER_FOOTER_COUNT[0]){
            return true;
        }else if(pos>=(childCount-HEADER_FOOTER_COUNT[0])){
            return true;
        }else{
            pos=pos-HEADER_FOOTER_COUNT[0];
            childCount=childCount-(HEADER_FOOTER_COUNT[0]+HEADER_FOOTER_COUNT[1]);
        }
        LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int orientation=((GridLayoutManager)layoutManager).getOrientation();
            if(orientation==GridLayoutManager.VERTICAL){
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount){return true;}
            }else{
                if ((pos + 1) % spanCount == 0){return true;}
            }
        }
        return false;
    }

    /**
     * int childCount=parent.getChildCount(); 无法获取正确数据
     * int childCount=parent.getAdapter().getItemCount();
     *
     * 最后一行item bottom 不偏移
     * 最后一列item right 不偏移
     * @param outRect
     * @param itemPosition
     * @param parent
     */
    @Override
    public void getItemOffsets(Rect outRect, int itemPosition,
                               RecyclerView parent) {
        GridLayoutManager gridLayoutManager = (GridLayoutManager) parent.getLayoutManager();
        int spanCount=gridLayoutManager.getSpanCount();
        int childCount=parent.getAdapter().getItemCount();

        outRect.set(
                0,0,
                isLastColumn(parent,itemPosition,spanCount,childCount)?0:mDivider.getIntrinsicWidth(),
                isLastRaw(parent,itemPosition,spanCount,childCount)?0:mDivider.getIntrinsicHeight());

    }
}