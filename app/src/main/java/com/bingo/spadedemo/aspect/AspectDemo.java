package com.bingo.spadedemo.aspect;

import android.util.Log;

import com.bingo.aspect.JoinPoint;
import com.bingo.aspect.annotation.PointCut;
import com.bingo.aspect.annotation.TargetClass;

public class AspectDemo {


    @TargetClass("com.bingo.spadedemo.ui.MainActivity")
    public void play(String message){
        Log.d("TAG", "我先调用");

        JoinPoint.Companion.proceed(message);

    }

}
