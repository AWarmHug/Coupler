package com.bingo.spadedemo.spade.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Keep;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.bingo.spadedemo.spade.activity.TActivity;
import com.bingo.spadedemo.theme.Theme;
import com.bingo.spadedemo.theme.ThemesKt;
import com.bingo.spadedemo.track.ViewTracker;

@Keep
public class TButton extends Button {
    public TButton(Context context) {
        this(context,null);
    }

    public TButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TButton(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public TButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (context instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) context;

            ThemesKt.getThemes().observe(activity, new Observer<Theme>() {
                @Override
                public void onChanged(Theme theme) {
                    Log.d("TAG", "onChanged: getBackgroundColor = " + theme.getBackgroundColor());
                    setTextColor(theme.getTextColor());
                    setBackgroundColor(theme.getBackgroundColor());
                }
            });
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

    }

    @Override
    public boolean performClick() {
        boolean click = super.performClick();
        ViewTracker.getInstance().performClick(this);
        return click;
    }


}
