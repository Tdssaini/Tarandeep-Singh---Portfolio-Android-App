package com.tarandeep.app.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tarandeep.app.Fragments.BaseFragment;
import com.tarandeep.app.Models.WordpressCategoryModel;

import java.util.ArrayList;

public class TDSViewPagerAdapter extends FragmentStatePagerAdapter {
	ArrayList<BaseFragment> fragments=null;
	ArrayList<WordpressCategoryModel> header = null;

	public TDSViewPagerAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments, ArrayList<WordpressCategoryModel> header){
		super(fm);
		this.fragments=fragments;
		this.header = header;
		notifyDataSetChanged();
	}
	@Override
	public Fragment getItem(int arg0) {
		return fragments.get(arg0);
	}
	@Override
	public int getCount() {
		return fragments.size();
	}
	@Override
	public CharSequence getPageTitle(int position) {
        DataUtil.sendLogsToLogger(DataPersistence.getInstance().getApplicationContext(),position+" "+header.get(position)+" getPageTitle");
		return header.get(position).getName();
	}
}