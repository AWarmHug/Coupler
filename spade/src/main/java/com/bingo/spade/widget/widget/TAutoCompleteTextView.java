package com.bingo.spade.widget.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

import com.bingo.spade.Track;


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
        Track.getTrack().getViewTracker().performClick(this);
        return click;
    }

}
