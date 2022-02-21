package com.bingo.spade;

import android.view.View;
import android.view.ViewGroup;

public class Spade {
    private static final Spade mInstance = new Spade();


    private Spade() {
    }


    public static Spade getSpade() {
        return mInstance;
    }



    public static void setChildNeedIndex(ViewGroup group) {
        group.setTag(R.id.key_child_need_index, true);
    }

    public static boolean isChildNeedIndex(ViewGroup group) {
        Object tag = group.getTag(R.id.key_child_need_index);
        if (tag instanceof Boolean) {
            return (boolean) tag;
        } else {
            return false;
        }
    }

    public static <T> void bindTrace(View view, T t) {
        view.setTag(R.id.key_track_content, t);
    }

    public static <T> T getTrace(View view) {
        Object tag = view.getTag(R.id.key_track_content);
        if (tag != null) {
            try {
                return (T) tag;
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }
}
