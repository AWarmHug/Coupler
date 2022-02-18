package com.bingo.spade.widget.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TableLayout;

import om.bingo.spade.widget.helper.AccessibilityDelegateHelper;
import com.bingo.spade.Track;

public class TTableLayout extends TableLayout {


    public TTableLayout(Context context) {
        super(context);
    }

    public TTableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean performClick() {
        boolean click = super.performClick();
        Track.getTrack().getViewTracker().performClick(this);
        return click;
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        AccessibilityDelegateHelper.onViewAdded(child);
    }
}
