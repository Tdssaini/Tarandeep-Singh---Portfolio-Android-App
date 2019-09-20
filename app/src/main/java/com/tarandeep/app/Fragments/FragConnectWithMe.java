package com.tarandeep.app.Fragments;

import com.tarandeep.app.Adapter.ConnectWithMeAdapter;
import com.tarandeep.app.Models.ConnectWithMeModel;
import com.tarandeep.app.Models.HomePageModel;
import com.tarandeep.app.R;
import com.tarandeep.app.Utils.DataPersistence;
import com.tarandeep.app.Utils.TDSGridView;

import java.util.ArrayList;

/**
 * Created by Tarandeep Singh on 5/25/2017.
 */

public class FragConnectWithMe extends BaseFragment {

    private TDSGridView gvConnectWithMe;

    @Override
    void getExtras() {}

    @Override
    int getLayoutId() {
        return R.layout.frag_connectwithme;
    }

    @Override
    void addListeners() {}

    @Override
    void setupBindings() {
        gvConnectWithMe.setExpanded(true,null);
        HomePageModel homePageModel = DataPersistence.getInstance().getHomePageModel();
        ArrayList<ConnectWithMeModel> connectWithMeModels = new ArrayList<>();
        connectWithMeModels.add(new ConnectWithMeModel("Website", WEBSITE_URL));
        connectWithMeModels.add(new ConnectWithMeModel("Blog", BLOG_URL));
        for(String contactKey : homePageModel.getContact_info().keySet()){
            connectWithMeModels.add(new ConnectWithMeModel(contactKey,homePageModel.getContact_info().get(contactKey)));
        }
        gvConnectWithMe.setAdapter(new ConnectWithMeAdapter(getParentActivity(),R.layout.connectwithme,connectWithMeModels));
    }

    @Override
    void initializeViews() {
        gvConnectWithMe = (TDSGridView)view.findViewById(R.id.gvConnectWithMe);
    }


}
