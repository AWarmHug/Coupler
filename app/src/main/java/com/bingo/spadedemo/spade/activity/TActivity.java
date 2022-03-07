package com.bingo.spadedemo.spade.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bingo.spadedemo.theme.Skin;

public class TActivity extends AppCompatActivity {
    private MutableLiveData<Skin> skinData =new MutableLiveData<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("Track", "context:" + this);
    }

    private void setSkin(Skin skin){
        skinData.setValue(skin);
    }

    private LiveData<Skin> getSkin(){
        return skinData;
    }


}
