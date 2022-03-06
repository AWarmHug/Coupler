package com.bingo.spadedemo.spade.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Keep;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.bingo.spadedemo.theme.Skin;
import com.bingo.spadedemo.theme.ViewTheme;
import com.bingo.spadedemo.theme.ThemesKt;
import com.bingo.spadedemo.track.ViewTracker;

@Keep
public class TButton extends Button {
    public TButton(Context context) {
        this(context, null);
    }

    public TButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TButton(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public TButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (context instanceof AppCompatActivity) {
//            AppCompatActivity activity = (AppCompatActivity) context;
//            Skin skin = ThemesKt.getSkin().getValue();
//            if (skin != null) {
//                skin.change(this);
//            }
//            ThemesKt.getSkin().observe(activity, new Observer<Skin>() {
//                @Override
//                public void onChanged(Skin skin) {
//                    skin.change(TButton.this);
//                }
//            });
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
