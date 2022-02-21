package com.bingo.spade;

import androidx.annotation.Nullable;

public interface Finder<T,F> {

    T find(F view);


}
