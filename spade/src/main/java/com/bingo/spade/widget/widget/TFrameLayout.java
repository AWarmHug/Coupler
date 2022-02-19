package com.bingo.spade.widget.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.bingo.spade.Spade;
import com.bingo.spade.widget.helper.AccessibilityDelegateHelper;

public class TFrameLayout extends FrameLayout {
    public TFrameLayout(Context context) {
        super(context);
    }

    public TFrameLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TFrameLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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
