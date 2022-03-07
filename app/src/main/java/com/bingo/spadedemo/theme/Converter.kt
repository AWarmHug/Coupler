package com.bingo.spadedemo.theme

interface Converter<F, Theme> {

    fun convert(f: F): Theme



}