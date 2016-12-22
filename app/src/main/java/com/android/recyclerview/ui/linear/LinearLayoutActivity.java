package com.android.recyclerview.ui.linear;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.recyclerview.R;
import com.android.recyclerview.decoration.LinearHeaderFooterItemDecotation;
import com.android.recyclerview.decoration.LinearSpaceItemDecoration;
import com.android.recyclerview.ui.linear.LinearLayoutAdapter;
import com.android.recyclerview.decoration.LinearItemDecoration;
import com.android.recyclerview.view.LRecyclerView;

/**
 * Description:
 * Author     : kevin.bai
 * Time       : 2016/12/12 14:46
 * QQ         : 904869397@qq.com
 */

public class LinearLayoutActivity extends AppCompatActivity implements LRecyclerView.Refresh{

    LRecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);

        mRecyclerView= (LRecyclerView) findViewById(R.id.lrv_linear);

        LinearLayoutManager vLinearLayoutManager=
                new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(vLinearLayoutManager);
        mRecyclerView.addItemDecoration(
                new LinearHeaderFooterItemDecotation(this,LinearLayoutManager.VERTICAL,new int[]{1,1}));
        mRecyclerView.setAdapter(new LinearLayoutAdapter(this));

        mRecyclerView.setRefreshEnabled(true);
        mRecyclerView.setLoadMoreEnabled(true);
        mRecyclerView.addHeaderView(
                LayoutInflater.from(this).inflate(R.layout.view_header,
                        (ViewGroup)findViewById(android.R.id.content),false));
        mRecyclerView.addFooterView(
                LayoutInflater.from(this).inflate(R.layout.view_footer,
                        (ViewGroup)findViewById(android.R.id.content),false));
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
