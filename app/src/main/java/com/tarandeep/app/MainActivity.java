package com.tarandeep.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.tarandeep.app.Fragments.BaseFragment;
import com.tarandeep.app.Fragments.FragAskQuestion;
import com.tarandeep.app.Fragments.FragCertifications;
import com.tarandeep.app.Fragments.FragConnectWithMe;
import com.tarandeep.app.Fragments.FragBlog;
import com.tarandeep.app.Fragments.FragHome;
import com.tarandeep.app.Fragments.FragNoNetwork;
import com.tarandeep.app.Fragments.FragResume;
import com.tarandeep.app.Fragments.FragSkillSet;
import com.tarandeep.app.Fragments.FragVideos;
import com.tarandeep.app.Utils.DataPersistence;
import com.tarandeep.app.Utils.DataUtil;
import com.tarandeep.app.Utils.IDS;
import com.tarandeep.app.Utils.PagerSlidingTabStrip;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener, IDS, OnPageChangeListener,OnLoadCompleteListener {

    private DrawerLayout drawer;
    public PagerSlidingTabStrip mTabLayout;
    public LinearLayout editContainerLinearLayout;
    public LinearLayout editContainerLinearLayout2;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fm;
    public boolean isEditPage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataPersistence.getInstance().setApplicationContext(this);
        editContainerLinearLayout = findViewById(R.id.editContainer_dataAct);
        editContainerLinearLayout2 = findViewById(R.id.editContainer2_dataAct);
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Tarandeep Singh");
        toolbar.setTitleTextColor(getResources().getColor(R.color.punjabBhawan));
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerArrowDrawable(new BadgedDrawerArrowDrawable(this));
        toggle.setDrawerIndicatorEnabled(true);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mTabLayout = findViewById(R.id.tab_layout_main);
        mTabLayout.setTextColor(Color.BLACK);
        mTabLayout.setTabPaddingLeftRight(10);
        mTabLayout.setIndicatorColor(getResources().getColor(android.R.color.black));
        mTabLayout.setTextSize(DataUtil.dipToPixels(12));
        loadDefaultLayout();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(isEditPage) {
                isEditPage = false;
                super.onBackPressed();
            }else{
                moveTaskToBack(true);
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = new Intent();
        mTabLayout.setVisibility(View.GONE);
        switch (item.getItemId()) {
            case R.id.home:
                replaceFragment(getFragment(HOME),null);
                mTabLayout.setVisibility(View.GONE);
                break;
            case R.id.blog:
                replaceFragment(getFragment(BLOG),null);
                mTabLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.askQuestion:
                replaceFragment(getFragment(ASK_QUESTION),null);
                mTabLayout.setVisibility(View.GONE);
                break;
            case R.id.skillset:
                replaceFragment(getFragment(SKILL_SET),null);
                mTabLayout.setVisibility(View.GONE);
                break;
            case R.id.certifications:
                replaceFragment(getFragment(CERTIFICATIONS),null);
                mTabLayout.setVisibility(View.GONE);
                break;
            case R.id.resume:
                Bundle bundle = new Bundle();
                bundle.putString("URL",DataPersistence.getInstance().getHomePageModel().getResume_link());
                replaceFragment(getFragment(RESUME),bundle);
                mTabLayout.setVisibility(View.GONE);
                break;
            case R.id.youtube:
                replaceFragment(getFragment(YOUTUBE),null);
                mTabLayout.setVisibility(View.GONE);
                break;
            case R.id.ConnectWithMe:
                replaceFragment(getFragment(CONNECT_WITH_ME),null);
                mTabLayout.setVisibility(View.GONE);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class BadgedDrawerArrowDrawable extends DrawerArrowDrawable {

        public BadgedDrawerArrowDrawable(Context context) {
            super(context);
            setColor(context.getResources().getColor(R.color.blackDark));
        }

        @Override
        public void draw(Canvas canvas) {
            super.draw(canvas);
        }
    }

    public BaseFragment getFragment(String selectedItem) {
        if(selectedItem.equals(HOME)){
            return new FragHome();
        }else if(selectedItem.equals(SKILL_SET)){
            return  new FragSkillSet();
        }else if(selectedItem.equals(BLOG)){
            return  new FragBlog();
        }else if(selectedItem.equals(ASK_QUESTION)) {
            return new FragAskQuestion();
        }else if(selectedItem.equals(CERTIFICATIONS)){
            return new FragCertifications();
        }else if(selectedItem.equals(RESUME)){
            return new FragResume();
        }else if(selectedItem.equals(YOUTUBE)){
            return new FragVideos();
        }else if(selectedItem.equals(CONNECT_WITH_ME)){
            return new FragConnectWithMe();
        }
        return  null;
    }

    public void loadDefaultLayout(){
        replaceFragment(getFragment(HOME),null);
        mTabLayout.setVisibility(View.GONE);
    }

    public void closeKeyboard(){
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    BaseFragment baseFragment;
    public  void replaceFragment(Fragment fragment, Bundle bundle){
        closeKeyboard();
        if(DataUtil.isNetworkAvailable(this)) {
            baseFragment = (BaseFragment) fragment;
        }else{
            baseFragment = new FragNoNetwork();
            mTabLayout.setVisibility(View.GONE);
        }

        fm = getSupportFragmentManager();
        fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.addToBackStack(null);

        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.content_layout, baseFragment);
        fragmentTransaction.commit();

        drawer.closeDrawer(GravityCompat.START);

    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }

    @Override
    public void loadComplete(int nbPages) {
        baseFragment.onPdfLoad(nbPages);
    }
}
