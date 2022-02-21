package com.bingo.spadedemo.spade.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TableLayout;

import com.bingo.spadedemo.spade.helper.AccessibilityDelegateHelper;
import com.bingo.spade.Spade;
import com.bingo.spadedemo.track.ViewTracker;

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
        ViewTracker.getInstance().performClick(this);
        return click;
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        AccessibilityDelegateHelper.onViewAdded(child);
    }
}
