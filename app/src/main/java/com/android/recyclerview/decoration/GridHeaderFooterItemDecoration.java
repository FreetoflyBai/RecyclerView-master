package com.android.recyclerview.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Description: GridLayout ItemDecoration
 *              包含Header和Footer判断方法，默认无Header与Footer，如有请传入数量
 *              默认不绘制Line，原因如下：
 *              parent.getChildCount() 获取结果为当前屏幕item总数，不是所有item总数
 *              parent.getAdapter().getItemCount() 可获取item总数，但是View绘制不能超出屏幕
 *              所以无法准确定位当前item位置，无法去除不应出现的线条
 * Author     : kevin.bai
 * Time       : 2016/12/5 11:54
 * QQ         : 904869397@qq.com
 */

public class GridHeaderFooterItemDecoration extends RecyclerView.ItemDecoration{
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };
    private int[] HEADER_FOOTER_COUNT=new int[]{
            0,0
    };
    private Drawable DIVIDER;

    public GridHeaderFooterItemDecoration(Context context,int[] headerFooterCount) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        DIVIDER = a.getDrawable(0);
        a.recycle();
        this.HEADER_FOOTER_COUNT=headerFooterCount;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

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
        }else if(pos>=(childCount-HEADER_FOOTER_COUNT[1])){
            return true;
        }else{
            pos=pos-HEADER_FOOTER_COUNT[0];
            childCount=childCount-(HEADER_FOOTER_COUNT[0]+HEADER_FOOTER_COUNT[1]);
        }
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int orientation=((GridLayoutManager)layoutManager).getOrientation();
            if(orientation==GridLayoutManager.VERTICAL){
                if ((pos + 1) % spanCount == 0){return true;}
            }else{
                if(childCount%spanCount==0){
                    childCount=childCount-spanCount;
                    if(pos>=childCount){return true;}
                }else{
                    childCount = childCount - childCount % spanCount;
                    if (pos >= childCount){return true;}
                }

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
        }else if(pos>=(childCount-HEADER_FOOTER_COUNT[1])){
            return true;
        }else{
            pos=pos-HEADER_FOOTER_COUNT[0];
            childCount=childCount-(HEADER_FOOTER_COUNT[0]+HEADER_FOOTER_COUNT[1]);
        }
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int orientation=((GridLayoutManager)layoutManager).getOrientation();
            if(orientation==GridLayoutManager.VERTICAL){
                if(childCount%spanCount==0){//整数倍spanCount
                    childCount=childCount-spanCount;
                    if(pos>=childCount){return true;}
                }else{//非整数倍spanCount
                    childCount = childCount - childCount % spanCount;
                    if (pos >= childCount){return true;}
                }
            }else{
                if ((pos + 1) % spanCount == 0){return true;}
            }
        }
        return false;
    }

    /**
     * int childCount=parent.getChildCount(); 只能获取当前屏幕item
     * int childCount=parent.getAdapter().getItemCount(); 可获取总数item
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
                isLastColumn(parent,itemPosition,spanCount,childCount)?0: DIVIDER.getIntrinsicWidth(),
                isLastRaw(parent,itemPosition,spanCount,childCount)?0: DIVIDER.getIntrinsicHeight());

    }

}
