package com.android.recyclerview.ui.grid;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.android.recyclerview.R;
import com.android.recyclerview.decoration.GridItemDecoration;
import com.android.recyclerview.decoration.GridSpaceItemDecoration;
import com.android.recyclerview.view.LRecyclerView;

/**
 * Description:
 * Author     : kevin.bai
 * Time       : 2016/12/12 15:03
 * QQ         : 904869397@qq.com
 */

public class GridLayoutActivity extends AppCompatActivity implements LRecyclerView.Refresh{

    LRecyclerView mRecyclerView;
    int spanCount=4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        mRecyclerView= (LRecyclerView) findViewById(R.id.lrv_grid);

        GridLayoutManager vGridLayoutManager=
                new GridLayoutManager(this,spanCount,GridLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(vGridLayoutManager);
        mRecyclerView.addItemDecoration(new GridItemDecoration(this));
        mRecyclerView.setAdapter(new GridLayoutAdapter(this));

        mRecyclerView.setRefreshEnabled(true);
        mRecyclerView.setLoadMoreEnabled(true);
        mRecyclerView.addHeaderView(
                View.inflate(this,R.layout.view_header,null));
        mRecyclerView.addFooterView(
                View.inflate(this,R.layout.view_footer,null));
        mRecyclerView.addRefresh(this);
    }

    @Override
    public void onRefresh() {
        new Handler().postAtTime(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setRefreshing(false);
            }
        },3000);
    }

    @Override
    public void onLoadMore() {

    }
}
