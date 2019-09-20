package com.tarandeep.app.Fragments;

import android.text.Html;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.tarandeep.app.Models.HomePageModel;
import com.tarandeep.app.R;
import com.tarandeep.app.Utils.DataPersistence;
import com.tarandeep.app.Utils.DataUtil;
import com.tarandeep.app.Utils.DownloadImageTask;
import com.tarandeep.app.Utils.TDSAsyncTaskLoader;

import java.util.ArrayList;
import java.util.Map;


/**
 * Created by Tarandeep Singh on 5/25/2017.
 */

public class FragHome extends BaseFragment {

    private WebView webView;

    @Override
    public int getLayoutId() {
        return R.layout.frag_home;
    }

    @Override
    void getExtras() {

    }

    @Override
    void addListeners() {

    }

    @Override
    void setupBindings() {
        getData();
    }

    @Override
    void initializeViews() {

    }

    private void getData(){
        TDSAsyncTaskLoader asyncTaskLoader = new TDSAsyncTaskLoader(getParentActivity(), new TDSAsyncTaskLoader.AsyncTaskLoaderListener() {
            @Override
            public void onPostExecute(Object result) throws Exception {
                HomePageModel homePageModel = (HomePageModel)DataUtil.stringToObject(result.toString(),HomePageModel.class);
                DataUtil.sendLogsToLogger(getParentActivity(),DataUtil.objectToString(homePageModel));
                DataPersistence.getInstance().setHomePageModel(homePageModel);
                setupData(homePageModel);
            }
        });
        asyncTaskLoader.setUrl(WEBSITE_URL + CUSTOM_WP_API+GET_HOME_PAGE_DATA);
        asyncTaskLoader.setRequestHeaderList(new ArrayList());
        asyncTaskLoader.setMethodType(GET);
        asyncTaskLoader.setRequestBodyList(new ArrayList());
        asyncTaskLoader.execute();
    }

    private void setupData(HomePageModel homePageModel){
        WebView wvExperience = (WebView)view.findViewById(R.id.wvExperience);
        wvExperience.loadUrl("file:///android_asset/experience.html");
        wvExperience.getSettings().setJavaScriptEnabled(true);
        wvExperience.getSettings().setLoadsImagesAutomatically(true);
        wvExperience.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        ImageView ivImage = (ImageView)view.findViewById(R.id.ivImage);
        new DownloadImageTask(ivImage).execute(homePageModel.getImage_url());
        for(Map<Object,Object> summary : homePageModel.getSummary()){
            if(summary.get("ID").toString().equalsIgnoreCase("796")){
                TextView tvSummmary = (TextView)view.findViewById(R.id.tvSummmary);
                Map<Object,Object> data = (Map<Object,Object>)DataUtil.stringToObject(summary.get("post_content").toString(),Map.class);
                Map<Object,Object> introData = (Map<Object,Object>)data.get("myresume::intro_text");
                tvSummmary.setText(Html.fromHtml(introData.get("value").toString()));
            }
        }
        TextView tvExperience = (TextView)view.findViewById(R.id.tvExperience);
        tvExperience.setText(Html.fromHtml(homePageModel.getExperience()));
        TextView tvEducation = (TextView)view.findViewById(R.id.tvEducation);
        String education= "";
        for(String educationKeys : homePageModel.getEducation().keySet()){
            education += educationKeys+". "+homePageModel.getEducation().get(educationKeys)+"]<br/>";
        }
        tvEducation.setText(Html.fromHtml(education.replace("<br />"," [")));
        TextView tvPersonalInfo = (TextView)view.findViewById(R.id.tvPersonalInfo);
        tvPersonalInfo.setText(Html.fromHtml(homePageModel.getPersonal_info()));
        TextView tvCurrentDesignation = (TextView)view.findViewById(R.id.tvCurrentDesignation);
        tvCurrentDesignation.setText(homePageModel.getCurrent_designation());

    }


}
