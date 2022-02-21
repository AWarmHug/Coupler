package com.bingo.spade;

import android.view.View;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Tracker<T,Target> {

    protected abstract T find(Target target);

    public abstract void track(@Nullable T t);


}
