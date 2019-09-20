package com.tarandeep.app.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * Created by Tarandeep
 */
public class TDSGridView extends GridView {

    private boolean expanded = false;
    private View parentView;

    public TDSGridView(Context context)
    {

        super(context);
    }

    public TDSGridView(Context context, AttributeSet attrs)
    {

        super(context, attrs);
    }

    public TDSGridView(Context context, AttributeSet attrs,int defStyle)
    {
        super(context, attrs, defStyle);
    }

    public boolean isExpanded()
    {
        return expanded;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        if (isExpanded())
        {
            int expandSpec = MeasureSpec.makeMeasureSpec(MEASURED_SIZE_MASK,
                    MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);

            ViewGroup.LayoutParams params = getLayoutParams();
            params.height = getMeasuredHeight();
            if(parentView !=null) {
                parentView.scrollTo(0, 0);
            }
        }
        else
        {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public void setExpanded(boolean expanded, View parentView)
    {
        this.expanded = expanded;
        this.parentView = parentView;
    }
}
