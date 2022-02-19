package com.bingo.spade.widget.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsoluteLayout;

import androidx.annotation.Nullable;

import com.bingo.spade.Spade;
import com.bingo.spade.widget.helper.AccessibilityDelegateHelper;

@Deprecated
public class TAbsoluteLayout extends AbsoluteLayout {
    public TAbsoluteLayout(Context context) {
        super(context);
    }

    public TAbsoluteLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TAbsoluteLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean performClick() {
        boolean click = super.performClick();
        Spade.getSpade().getViewTracker().performClick(this);
        return click;
    }


    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        AccessibilityDelegateHelper.onViewAdded(child);
    }


}
