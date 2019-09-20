package com.tarandeep.app.Fragments;

import android.content.Context;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.tarandeep.app.Models.WordpressCategoryModel;
import com.tarandeep.app.Models.WordpressPostModel;
import com.tarandeep.app.R;
import com.tarandeep.app.Utils.DataUtil;
import com.tarandeep.app.Utils.TDSAsyncTaskLoader;
import com.tarandeep.app.Utils.WPPostViewAdapter;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Tarandeep on 6/2/2016.
 */
public class FragCategoryPosts extends BaseFragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listview;
    private int color = 0;
    private WordpressCategoryModel categoryModel;
    private View loadMoreView;
    private boolean loadingMore = false;
    private int pageNo;
    private WPPostViewAdapter wPPostViewAdapter;

    @Override
    int getLayoutId() {
        return R.layout.fragment_category_post;
    }

    @Override
    void initializeViews() {
        listview = (ListView) view.findViewById(R.id.recycler_view_recycler_view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            listview.setNestedScrollingEnabled(true);
        }
    }

    @Override
    void getExtras() {
        categoryModel = (WordpressCategoryModel)getArguments().getSerializable("CAT");
    }

    @Override
    void addListeners() {
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int lastInScreen = firstVisibleItem + visibleItemCount;
                if ((lastInScreen == totalItemCount) && !(loadingMore)) {
                    pageNo = pageNo+ 1;
                    getData();
                }
            }
        });
    }

    @Override
    void setupBindings() {
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh_layout_recycler_view);
        swipeRefreshLayout.setColorSchemeResources(R.color.blue, R.color.greenDark, R.color.red, R.color.yellowDark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNo = 1;
                FragCategoryPosts.this.getData();
            }
        });
        pageNo = 1;
        wPPostViewAdapter = new WPPostViewAdapter(getParentActivity(),new ArrayList<WordpressPostModel>());
        loadMoreView = ((LayoutInflater)getParentActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.loadmore, null, false);
        listview.setAdapter(wPPostViewAdapter);
        listview.addFooterView(loadMoreView);
        getData();

    }

    private void getData(){
        TDSAsyncTaskLoader asyncTaskLoader = new TDSAsyncTaskLoader(getParentActivity(), new TDSAsyncTaskLoader.AsyncTaskLoaderListener() {
            @Override
            public void onPostExecute(Object result) throws Exception {
                if(result.toString().trim().equals("")){
                    listview.removeFooterView(loadMoreView);
                }
                else {
                    JSONArray jsonArray = new JSONArray(result.toString());
                    for(int i=0;i<jsonArray.length();i++){
                        wPPostViewAdapter.add((WordpressPostModel) DataUtil.stringToObject(jsonArray.get(i).toString(),WordpressPostModel.class));
                    }
                    wPPostViewAdapter.notifyDataSetChanged();

                    if (jsonArray.length() < 10) {
                        listview.removeFooterView(loadMoreView);
                    }
                    if(jsonArray.length()==10){
                        loadingMore = false;
                    }
                }
            }
        },false){
            @Override
            protected Object doInBackground(String... params) {
                loadingMore = true;
                return super.doInBackground(params);
            }
        };
        asyncTaskLoader.setUrl(BLOG_URL + WP_API+GET_ALL_POSTS+"/");
        ArrayList<NameValuePair> bodyList = new ArrayList<>();
        bodyList.add(new BasicNameValuePair("per_page","10"));
        bodyList.add(new BasicNameValuePair("categories",categoryModel.getId()+""));
        bodyList.add(new BasicNameValuePair("page",pageNo+""));
        asyncTaskLoader.setRequestHeaderList(new ArrayList<>());
        asyncTaskLoader.setRequestBodyList(bodyList);
        asyncTaskLoader.setMethodType(GET);
        asyncTaskLoader.execute();
    }

}