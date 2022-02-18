package com.bingo.spade.widget.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RatingBar;

import com.bingo.spade.Track;

public class TRatingBar extends RatingBar {
    public TRatingBar(Context context) {
        super(context);
    }

    public TRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TRatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean performClick() {
        boolean click = super.performClick();
        Track.getTrack().getViewTracker().performClick(this);
        return click;
    }

}
