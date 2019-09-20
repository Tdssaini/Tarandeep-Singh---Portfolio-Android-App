package com.tarandeep.app.Utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class TDSViewPager extends ViewPager {

	private boolean isPagingEnabled;
	
	public TDSViewPager(Context context) {
		super(context);
	}

    public TDSViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.isPagingEnabled = false;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.isPagingEnabled) {
            return super.onTouchEvent(event);
        }
        return false;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.isPagingEnabled) {
            return super.onInterceptTouchEvent(event);
        }
        return false;
    }

    public void setPagingEnabled(boolean b) {
        this.isPagingEnabled = b;
    }
}
