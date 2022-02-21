package com.bingo.spadedemo.spade;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.bingo.spadedemo.spade.widget.TAbsoluteLayout;
import com.bingo.spadedemo.spade.widget.TFrameLayout;
import com.bingo.spadedemo.spade.widget.TLinearLayout;
import com.bingo.spadedemo.spade.widget.TRelativeLayout;
import com.bingo.spadedemo.spade.widget.TScrollView;
import com.bingo.spadedemo.spade.widget.TTableLayout;
import com.google.android.material.theme.MaterialComponentsViewInflater;

public class TAppCompatViewInflater extends MaterialComponentsViewInflater {

    @Nullable
    @Override
    protected View createView(Context context, String name, AttributeSet attrs) {
        switch (name) {
            case "LinearLayout":
                return new TLinearLayout(context, attrs);
            case "FrameLayout":
                return new TFrameLayout(context, attrs);
            case "TableLayout":
                return new TTableLayout(context, attrs);
            case "RelativeLayout":
                return new TRelativeLayout(context, attrs);
            case "TScrollView":
                return new TScrollView(context, attrs);
            case "AbsoluteLayout":
                return new TAbsoluteLayout(context, attrs);
            default:
                return null;
        }

    }
}
