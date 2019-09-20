package com.tarandeep.app.Fragments;

import android.view.View;
import android.widget.Button;

import com.tarandeep.app.R;
import com.tarandeep.app.Utils.TDSDrawable;


/**
 * Created by Tarandeep on 12/19/2015.
 */
public class FragNoNetwork extends BaseFragment {

    private Button btnRetry;

    @Override
    void getExtras() {}

    @Override
    int getLayoutId() {
        return R.layout.frag_no_network;
    }

    @Override
    void addListeners() {
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentActivity().loadDefaultLayout();
            }
        });
    }

    @Override
    void setupBindings() {
        btnRetry.setBackground(TDSDrawable.getSelectorDefault(getParentActivity(), "blue_btn"));
    }

    @Override
    void initializeViews() {
        btnRetry = (Button)view.findViewById(R.id.btnRetry);
    }
}
