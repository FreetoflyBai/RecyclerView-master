package com.android.recyclerview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.recyclerview.R;

/**
 * Description:
 * Author     : kevin.bai
 * Time       : 2016/12/7 17:36
 * QQ         : 904869397@qq.com
 */

public class LRecyclerView extends LinearLayout
        implements SwipeRefreshLayout.OnRefreshListener{

    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView mRecyclerView;
    Refresh mRefresh;
    private boolean mLoadMoreEnabled=false;
    LRecyclerViewAdapter mLRecyclerViewAdapter;
    RecyclerView.Adapter mTrueAdapter;

    public LRecyclerView(Context context) {
        super(context);
        initView(context);
    }

    public LRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        View view=View.inflate(context, R.layout.view_lrv,null);
        mSwipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.srl);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(context, R.color.colorPrimary),
                ContextCompat.getColor(context, R.color.colorAccent),
                ContextCompat.getColor(context, R.color.colorPrimaryDark)
        );
        mSwipeRefreshLayout.setEnabled(false);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.rv);
        mRecyclerView.addOnScrollListener(onScrollListener);
        this.addView(view);

    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        if(mRefresh==null){
            return;
        }
        mRefresh.onRefresh();

    }

    /**
     * 上拉加载更多
     */
    RecyclerView.OnScrollListener onScrollListener=new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            //1  表示底部 -1 表示顶部
            if(!recyclerView.canScrollVertically(1)&&mLoadMoreEnabled){
                if(mRefresh==null){
                    return;
                }
                mRefresh.onLoadMore();
            }
            super.onScrollStateChanged(recyclerView, newState);
        }
    };

    /**
     * 添加下拉和上拉监听
     * @param refresh
     */
    public void addRefresh(Refresh refresh){
        this.mRefresh=refresh;
    }

    /**
     * 是否启用下拉刷新功能
     * 默认关闭
     * @param active
     */
    public void setRefreshEnabled(boolean active){
        if(mSwipeRefreshLayout==null){
            return;
        }
        mSwipeRefreshLayout.setEnabled(active);
    }

    /**
     * 是否启用上拉加载更多功能
     * 默认关闭
     * @param active
     */
    public void setLoadMoreEnabled(boolean active){
        this.mLoadMoreEnabled=active;
    }

    /**
     * 开启或关闭下拉刷新
     * @param active
     */
    public void setRefreshing(boolean active){
        if(mSwipeRefreshLayout==null){
            return;
        }
        mSwipeRefreshLayout.setRefreshing(active);
    }

    /**
     * 设置下拉刷新颜色
     * @param colors
     */
    public void setColorSchemeColors(int... colors){
        mSwipeRefreshLayout.setColorSchemeColors(colors);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layout) {
        mRecyclerView.setLayoutManager(layout);
    }

    public void addItemDecoration(RecyclerView.ItemDecoration decor) {
        mRecyclerView.addItemDecoration(decor);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        mTrueAdapter=adapter;
        mLRecyclerViewAdapter=new LRecyclerViewAdapter(adapter);
        mRecyclerView.setAdapter(mLRecyclerViewAdapter);
    }

    /**
     * 刷新数据
     * 使用adapter刷新不会有作用，因为外层有包装
     */
    public void notifyDataSetChanged(){
        if(mLRecyclerViewAdapter==null||mTrueAdapter==null){
            return;
        }
        mLRecyclerViewAdapter.notifyDataSetChanged();
        mTrueAdapter.notifyDataSetChanged();

    }

    /**
     * 添加Header
     * 需要在setAdapter 之后
     * @param view
     */
    public void addHeaderView(View view){
        if(mLRecyclerViewAdapter==null){
            return;
        }
        mLRecyclerViewAdapter.addHeaderView(view);
    }

    /**
     * 删除Header
     * @param view
     */
    public void removeHeaderView(View view){
        if(mLRecyclerViewAdapter==null){
            return;
        }
        mLRecyclerViewAdapter.removeHeaderView(view);
    }

    /**
     * 获取Header
     * @return
     */
    public View getHeaderView(){
        if(mLRecyclerViewAdapter==null){
            return null;
        }
        return mLRecyclerViewAdapter.getHeaderView();
    }

    /**
     * 获取Header数量
     * @return
     */
    public int getHeaderViewCount(){
        if(mLRecyclerViewAdapter==null){
            return -1;
        }
        return mLRecyclerViewAdapter.getHeaderViewsCount();
    }
    /**
     * 添加Footer
     * 需要在setAdapter 之后
     * @param view
     */
    public void addFooterView(View view){
        if(mLRecyclerViewAdapter==null){
            return;
        }
        mLRecyclerViewAdapter.addFooterView(view);
    }

    /**
     * 获取Footer数量
     * @return
     */
    public int getFooterViewCount(){
        if(mLRecyclerViewAdapter==null){
            return -1;
        }
        return mLRecyclerViewAdapter.getFooterViewsCount();
    }

    /**
     * 删除Footer
     * @param view
     */
    public void removeFooterView(View view){
        if(mLRecyclerViewAdapter==null){
            return;
        }
        mLRecyclerViewAdapter.removeFooterView(view);
    }

    /**
     * 获取Footer
     * @return
     */
    public View getFooterView(){
        if(mLRecyclerViewAdapter==null){
            return null;
        }
        return mLRecyclerViewAdapter.getFooterView();
    }

    /**
     * 获取adapter
     * @return
     */
    public LRecyclerViewAdapter getAdapter(){
        return mLRecyclerViewAdapter;
    }

    /**
     * 设置item点击事件
     * 需要在setAdapter 之后
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        if(mLRecyclerViewAdapter==null){
            return;
        }
        mLRecyclerViewAdapter.setOnItemClickListener(onItemClickListener);
    }

    /**
     * 设置item长按事件
     * 需要在setAdapter 之后
     * @param onItemLongClickListener
     */
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener){
        if(mLRecyclerViewAdapter==null){
            return;
        }
        mLRecyclerViewAdapter.setOnItemLongClickListener(onItemLongClickListener);
    }

    /**
     * 回调接口，封装下拉刷新和上拉加载更多
     */
    public interface Refresh{
        abstract void onRefresh();
        abstract void onLoadMore();
    }
}
