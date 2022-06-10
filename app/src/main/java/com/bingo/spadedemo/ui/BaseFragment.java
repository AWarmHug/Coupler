package com.bingo.spadedemo.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class BaseFragment extends Fragment {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setTag(com.bingo.spade.R.id.key_extra_name, getClass().getSimpleName());
    }

    @Nullable
    public CharSequence getTitle() {
        return null;
    }
}
