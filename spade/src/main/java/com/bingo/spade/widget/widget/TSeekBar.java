package com.bingo.spade.widget.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;

import com.bingo.spade.Track;

public class TSeekBar extends SeekBar {
    public TSeekBar(Context context) {
        super(context);
    }

    public TSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean performClick() {
        boolean click = super.performClick();
        Track.getTrack().getViewTracker().performClick(this);
        return click;
    }


}
