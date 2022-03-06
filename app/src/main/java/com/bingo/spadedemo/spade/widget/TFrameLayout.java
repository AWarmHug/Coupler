package com.bingo.spadedemo.spade.widget;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.bingo.spade.Spade;
import com.bingo.spadedemo.R;
import com.bingo.spadedemo.spade.helper.AccessibilityDelegateHelper;
import com.bingo.spadedemo.theme.Background;
import com.bingo.spadedemo.theme.Skin;
import com.bingo.spadedemo.theme.ThemesKt;
import com.bingo.spadedemo.theme.ViewTheme;
import com.bingo.spadedemo.track.ViewTracker;

public class TFrameLayout extends FrameLayout {

    final Object lock=new Object();

    public TFrameLayout(Context context) {
        this(context, null);
    }

    public TFrameLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TFrameLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (context instanceof AppCompatActivity) {

            synchronized (lock) {

                Log.d("TAG", "TFrameLayout: "+this.getId());
                Log.d("TAG", "content: "+R.id.c);

                if (getId() != R.id.c) {
                    return;
                }
                AppCompatActivity activity = (AppCompatActivity) context;
//                Skin skin = ThemesKt.getSkin().getValue();
//                if (skin != null) {
//                    skin.change(this);
//                }
                Log.d("TAG", "TFrameLayout000: "+this.getId()+"sss="+this);
                ThemesKt.getSkin().observe(activity, new Observer<Skin>() {
                    @Override
                    public void onChanged(Skin skin) {
                        if (skin != null) {
                            skin.change(TFrameLayout.this);
                        }
                    }
                });
            }
        }
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
