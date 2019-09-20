package com.tarandeep.app.Fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import com.tarandeep.app.Models.WordpressCategoryModel;
import com.tarandeep.app.R;
import com.tarandeep.app.Utils.DataUtil;
import com.tarandeep.app.Utils.TDSAsyncTaskLoader;
import com.tarandeep.app.Utils.TDSViewPager;
import com.tarandeep.app.Utils.TDSViewPagerAdapter;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Tarandeep Singh on 5/21/2017.
 */

public class FragBlog extends BaseFragment {

    TDSViewPager viewPager=null;
    TDSViewPagerAdapter altaViewPagerAdapter=null;

    @Override
    void getExtras() {}

    @Override
    int getLayoutId() {
        return R.layout.frag_blog;
    }

    @Override
    void addListeners() {}
    @Override
    void setupBindings() {
        getAllNews();
    }

    private void getAllNews(){
        TDSAsyncTaskLoader asyncTaskLoader = new TDSAsyncTaskLoader(getParentActivity(), new TDSAsyncTaskLoader.AsyncTaskLoaderListener() {
            @Override
            public void onPostExecute(Object result) throws Exception {
                ArrayList<WordpressCategoryModel> categories = new ArrayList<>();
                JSONArray jsonArray = new JSONArray(result.toString());
                for(int i=0;i<jsonArray.length();i++) {
                    WordpressCategoryModel category =
                            (WordpressCategoryModel)DataUtil.stringToObject(jsonArray.getJSONObject(i).toString(), WordpressCategoryModel.class);
                    categories.add(category);

                    ArrayList<BaseFragment> fragments = new ArrayList<>();
                    for(WordpressCategoryModel cat :categories) {
                        FragCategoryPosts categoryNews = new FragCategoryPosts();
                        Bundle bundle = new Bundle();
                        bundle.putString("HEADER_NAME",cat.getName());
                        bundle.putSerializable("CAT",cat);
                        categoryNews.setArguments(bundle);
                        fragments.add(categoryNews);
                    }
                    FragmentManager man=getChildFragmentManager();
                    altaViewPagerAdapter=new TDSViewPagerAdapter(man, fragments, categories);
                    viewPager.setAdapter(altaViewPagerAdapter);
                    viewPager.invalidate();
                    viewPager.setCurrentItem(0);
                    viewPager.setOffscreenPageLimit(2);
                    getParentActivity().mTabLayout.setViewPager(viewPager);
                }

            }
        });
        asyncTaskLoader.setUrl(BLOG_URL + WP_API+GET_ALL_CATEGORIES);
        asyncTaskLoader.setRequestHeaderList(new ArrayList());
        asyncTaskLoader.setMethodType(GET);
        asyncTaskLoader.setRequestBodyList(new ArrayList());
        asyncTaskLoader.execute();

    }

    @Override    void initializeViews() {
        viewPager=(TDSViewPager)view.findViewById(R.id.fragmentLoader);
        viewPager.setPagingEnabled(true);

    }

}
