package com.bingo.spadedemo.spade.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.bingo.spade.Spade;
import com.bingo.spadedemo.track.ViewTracker;

public class TView extends View {
    public TView(Context context) {
        super(context);
    }

    public TView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean performClick() {
        boolean click = super.performClick();
        ViewTracker.getInstance().performClick(this);
        return click;
    }


}
