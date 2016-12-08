package com.android.recyclerview.base;

import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.recyclerview.R;
import com.android.recyclerview.grid.GridItemDecoration;
import com.android.recyclerview.grid.GridSpaceItemDecoration;
import com.android.recyclerview.linear.LinearItemDecoration;
import com.android.recyclerview.linear.LinearSpaceItemDecoration;
import com.android.recyclerview.staggered.StaggeredGridItemDecoration;
import com.android.recyclerview.view.LRecyclerView;

import static com.android.recyclerview.R.array.item;

/**
 * Description:
 * Author     : kevin.bai
 * Time       : 2016/12/5 11:54
 * QQ         : 904869397@qq.com
 */
public class MainActivity extends AppCompatActivity{

    LRecyclerView mRecyclerView;
    View mHeader;
    View mFooter;
    int spanCount=4;


    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView= (LRecyclerView) findViewById(R.id.lrv);

    }

    private void initRecyclerView(){
        mRecyclerView.setRefreshEnabled(true);
        mRecyclerView.setLoadMoreEnabled(true);
        if(mRecyclerView.getHeaderViewCount()<1){
            mHeader=View.inflate(this,R.layout.view_header,null);
            mRecyclerView.addHeaderView(mHeader);
        }
        if(mRecyclerView.getFooterViewCount()<1){
            mFooter=View.inflate(this,R.layout.view_footer,null);
            mRecyclerView.addFooterView(mFooter);
        }
        mRecyclerView.addRefresh(refresh);
    }

    LRecyclerView.Refresh refresh=new LRecyclerView.Refresh() {
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
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_linear_hor:
                linearLayoutManagerHorizontal();
                break;
            case R.id.menu_linear_ver:
                linearLayoutManagerVertical();
                break;
            case R.id.menu_grid_hor:
                gridLayoutManagerHorizontal();
                break;
            case R.id.menu_grid_ver:
                gridLayoutManagerVertical();
                break;
            case R.id.menu_staggered_hor:
                staggeredGridManagerHorizontal();
                break;
            case R.id.menu_staggered_ver:
                staggeredGridManagerVertical();
                break;
        }
        initRecyclerView();
        return super.onOptionsItemSelected(item);
    }

    /**
     * 线性横向滚动
     */
    private void linearLayoutManagerHorizontal(){
        LinearLayoutManager hLinearLayoutManager=
                new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(hLinearLayoutManager);
        mRecyclerView.addItemDecoration(new LinearItemDecoration(this,LinearLayoutManager.HORIZONTAL));
        mRecyclerView.setAdapter(new MainAdapter(this,false,-1));
    }

    /**
     * 线性纵向滚动
     */
    private void linearLayoutManagerVertical(){
        LinearLayoutManager vLinearLayoutManager=
                new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(vLinearLayoutManager);
        mRecyclerView.addItemDecoration(new LinearItemDecoration(this,LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(new MainAdapter(this,false,-1));
    }

    /**
     * Grid横向滚动
     */
    private void gridLayoutManagerHorizontal(){
        GridLayoutManager hGridLayoutManager=
                new GridLayoutManager(this,spanCount,GridLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(hGridLayoutManager);
        mRecyclerView.addItemDecoration(new GridItemDecoration(this));
        mRecyclerView.setAdapter(new MainAdapter(this,false,-1));
    }

    /**
     * Grid纵向滚动
     */
    private void gridLayoutManagerVertical(){
        GridLayoutManager vGridLayoutManager=
                new GridLayoutManager(this,spanCount,GridLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(vGridLayoutManager);
        mRecyclerView.addItemDecoration(new GridItemDecoration(this));
        mRecyclerView.setAdapter(new MainAdapter(this,false,-1));
    }

    /**
     * Staggered横向滚动
     */
    private void staggeredGridManagerHorizontal(){
        StaggeredGridLayoutManager hStaggeredGridLayoutManager=
                new StaggeredGridLayoutManager(spanCount,StaggeredGridLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(hStaggeredGridLayoutManager);
        mRecyclerView.addItemDecoration(new StaggeredGridItemDecoration(this));
        mRecyclerView.setAdapter(new MainAdapter(this,true,StaggeredGridLayoutManager.HORIZONTAL));
    }

    /**
     * Staggered纵向滚动
     */
    private void staggeredGridManagerVertical(){
        StaggeredGridLayoutManager vStaggeredGridLayoutManager=
                new StaggeredGridLayoutManager(spanCount,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(vStaggeredGridLayoutManager);
        mRecyclerView.addItemDecoration(new StaggeredGridItemDecoration(this));
        mRecyclerView.setAdapter(new MainAdapter(this,true,StaggeredGridLayoutManager.VERTICAL));
    }
}
