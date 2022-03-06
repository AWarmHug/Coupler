package com.bingo.spadedemo.spade.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import androidx.appcompat.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.bingo.spadedemo.theme.Skin;
import com.bingo.spadedemo.theme.ThemesKt;
import com.bingo.spadedemo.track.ViewTracker;

public class TCheckBox extends CheckBox {
    public TCheckBox(Context context) {
        this(context,null);
    }

    public TCheckBox(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.checkboxStyle);
    }

    public TCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (context instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) context;
            ThemesKt.getSkin().observe(activity, new Observer<Skin>() {
                @Override
                public void onChanged(Skin skin) {
                    skin.binding(TCheckBox.this);
                }
            });
        }
    }

    @Override
    public void setChecked(boolean checked) {
        boolean c = isChecked() != checked;
        super.setChecked(checked);
        if (c) {
            ViewTracker.getInstance().setChecked(this, checked);
        }
    }

}
