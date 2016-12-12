package com.android.recyclerview.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.android.recyclerview.R;
import com.android.recyclerview.decoration.LinearItemDecoration;
import com.android.recyclerview.decoration.LinearSpaceItemDecoration;
import com.android.recyclerview.ui.grid.GridLayoutActivity;
import com.android.recyclerview.ui.linear.LinearLayoutActivity;
import com.android.recyclerview.ui.staggered.StaggeredGridActivity;
import com.android.recyclerview.ui.staggered.StaggeredGridAdapter;
import com.android.recyclerview.view.LRecyclerView;
import com.android.recyclerview.view.OnItemClickListener;

/**
 * Description:
 * Author     : kevin.bai
 * Time       : 2016/12/5 11:54
 * QQ         : 904869397@qq.com
 */
public class MainActivity extends AppCompatActivity{

    LRecyclerView mRecyclerView;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView= (LRecyclerView) findViewById(R.id.lrv_main);
        mRecyclerView.setAdapter(new MainAdapter(this));
        LinearLayoutManager vLinearLayoutManager=
                new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(vLinearLayoutManager);
        mRecyclerView.addItemDecoration(
                new LinearSpaceItemDecoration(10,LinearLayoutManager.VERTICAL));
        mRecyclerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                intentTo(position);
            }
        });

    }

    private void intentTo(int position){
        Intent intent=new Intent();
        switch (position){
            case 0:
                intent.setClass(this,LinearLayoutActivity.class);
                break;
            case 1:
                intent.setClass(this,GridLayoutActivity.class);
                break;
            case 2:
                intent.setClass(this,StaggeredGridActivity.class);
                break;
        }
        startActivity(intent);
    }


}
