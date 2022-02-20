package com.bingo.spade.widget;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.CompoundButton;

import com.bingo.spade.Spade;

public class TrackingAccessibilityDelegate extends View.AccessibilityDelegate {

    @Override
    public void sendAccessibilityEvent(View host, int eventType) {
        super.sendAccessibilityEvent(host, eventType);
        switch (eventType) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                if (host instanceof CompoundButton) {
                    Spade.getSpade().getViewTracker().setChecked(host, ((CompoundButton) host).isChecked());
                } else {
                    Spade.getSpade().getViewTracker().performClick(host);
                }
                break;
        }
    }

    @Override
    public void sendAccessibilityEventUnchecked(View host, AccessibilityEvent event) {
        super.sendAccessibilityEventUnchecked(host, event);
    }


}