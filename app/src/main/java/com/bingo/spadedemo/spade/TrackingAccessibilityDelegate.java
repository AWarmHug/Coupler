package com.bingo.spadedemo.spade;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.CompoundButton;

import com.bingo.spade.Spade;
import com.bingo.spadedemo.track.ViewTracker;

public class TrackingAccessibilityDelegate extends View.AccessibilityDelegate {

    @Override
    public void sendAccessibilityEvent(View host, int eventType) {
        super.sendAccessibilityEvent(host, eventType);
        switch (eventType) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                if (host instanceof CompoundButton) {
                    ViewTracker.getInstance().setChecked(host, ((CompoundButton) host).isChecked());
                } else {
                    ViewTracker.getInstance().performClick(host);
                }
                break;
        }
    }

    @Override
    public void sendAccessibilityEventUnchecked(View host, AccessibilityEvent event) {
        super.sendAccessibilityEventUnchecked(host, event);
    }


}
