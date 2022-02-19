package com.bingo.spadedemo.track;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.bingo.spade.ViewFinder;
import com.bingo.spade.ViewTracker;


public class DefaultViewTracker extends ViewTracker<ViewTrace> {

    private ViewFinder<ViewTrace> mViewFinder;

    public DefaultViewTracker(ViewFinder<ViewTrace> viewFinder) {
        mViewFinder = viewFinder;
    }

    @Override
    public ViewTrace find(View view) {
        return mViewFinder.find(view);
    }

    @Override
    public void track(ViewTrace trace) {
        if (trace.getTrace() == null) {
            return;
        }
        track(trace.getContext(), trace.getTrace().getId(), trace.getTrace().getValue());


    }

    @Override
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
