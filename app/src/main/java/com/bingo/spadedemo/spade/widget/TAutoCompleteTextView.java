package com.bingo.spadedemo.spade.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

import com.bingo.spade.Spade;
import com.bingo.spadedemo.track.ViewTracker;


public class TAutoCompleteTextView extends AutoCompleteTextView {
    public TAutoCompleteTextView(Context context) {
        super(context);
    }

    public TAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TAutoCompleteTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean performClick() {
        boolean click = super.performClick();
        ViewTracker.getInstance().performClick(this);
        return click;
    }

}
