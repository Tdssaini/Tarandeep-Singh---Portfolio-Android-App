package com.tarandeep.app.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tarandeep.app.MainActivity;
import com.tarandeep.app.Utils.IDS;


/**
 * Created by Tarandeep on 12/31/2015.
 */
public abstract class BaseFragment extends Fragment implements IDS {

    protected View view;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    abstract int getLayoutId();
    abstract void initializeViews();
    abstract void getExtras();
    abstract void setupBindings();
    abstract void addListeners();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(),container,false);
        initializeViews();
        getExtras();
        setupBindings();
        addListeners();

        return view;
    }

    public MainActivity getParentActivity(){
        return (MainActivity) getActivity();
    }

    protected void onBackPressed(){
        getParentActivity().onBackPressed();
    }

    protected void clearBackStack() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    public  void onPdfLoad(int nPages){

    }

}
