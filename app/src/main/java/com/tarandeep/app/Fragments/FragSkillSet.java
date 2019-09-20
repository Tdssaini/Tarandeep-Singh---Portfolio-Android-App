package com.tarandeep.app.Fragments;

import com.tarandeep.app.Models.SkillSetModel;
import com.tarandeep.app.R;
import com.tarandeep.app.Utils.DataPersistence;
import com.tarandeep.app.Utils.DataUtil;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Tarandeep Singh on 5/25/2017.
 */

public class FragSkillSet extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.frag_skill_set;
    }

    @Override
    void getExtras() {

    }

    @Override
    void addListeners() {

    }

    @Override
    void setupBindings() {
        ArrayList<SkillSetModel> skillSetModels = new ArrayList<>();
        for(Map<Object,Object> summary :DataPersistence.getInstance().getHomePageModel().getSummary()){
            if(summary.get("ID").toString().equalsIgnoreCase("797")){
                Map<Object,Object> data = (Map<Object,Object>) DataUtil.stringToObject(summary.get("post_content").toString(),Map.class);
               // SkillSetModel skillSetModel = new SkillSetModel(((Map<Object,Object>)data.get("myresume::prof_1")).get("value").toString(),((Map<Object,Object>)data.get("myresume::prof_1")).get("value").toString());
            }
        }
    }

    @Override
    void initializeViews() {

    }


}
