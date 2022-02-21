package com.bingo.spadedemo.track;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.bingo.spade.Tracker;


public class ViewTracker extends Tracker<ViewTrace,View> {

    private static ViewTracker INSTANCE=new ViewTracker();

    public static ViewTracker getInstance(){
        return INSTANCE;
    }

    private DefaultFinder mDefaultFinder;

    private ViewTracker() {
        mDefaultFinder = new DefaultFinder();
    }

    public void performClick(View view) {
        ViewTrace t = find(view);
        track(t);
    }


    public void setChecked(View view, boolean checked) {
        ViewTrace t = find(view);
        track(t, checked);
    }


    @Override
    protected ViewTrace find(View target) {
        return mDefaultFinder.find(target);
    }

    @Override
    public void track(ViewTrace trace) {
        if (trace.getTrace() == null) {
            return;
        }
        track(trace.getContext(), trace.getTrace().getId(), trace.getTrace().getValue());


    }

    public void track(ViewTrace trace, boolean checked) {
        if (trace.getTrace() == null) {
            return;
        }
        if (!TextUtils.isEmpty(trace.getTrace().getValue()) && trace.getTrace().getValue().contains(ViewTrace.Trace.or)) {
            String[] checkValue = trace.getTrace().getValue().split(ViewTrace.Trace.or);
            track(trace.getContext(), trace.getTrace().getId(), checked ? checkValue[0] : checkValue[1]);
        } else {
            track(trace);
        }
    }

    private void track(Context context, String eventId, String value) {
        Log.d("Track", "context:" + context.toString() + "track: eventId=" + eventId + ",action=" + value);
    }
}
