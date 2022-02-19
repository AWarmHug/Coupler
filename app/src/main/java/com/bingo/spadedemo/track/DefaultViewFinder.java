package com.bingo.spadedemo.track;

import android.view.View;

import com.bingo.spade.Spade;
import com.bingo.spade.ViewFinder;

public class DefaultViewFinder extends ViewFinder<ViewTrace> {

    @Override
    public ViewTrace find(View view) {
        if (Spade.getTrace(view) != null) {
            return new ViewTrace(view.getContext(), Spade.getTrace(view));
        }
        return new ViewTrace(view.getContext(), Data.getEvent(getName(view)));
    }

}