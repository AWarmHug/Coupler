package com.bingo.aspect.annotation


@Retention(AnnotationRetention.RUNTIME)
@Target( AnnotationTarget.FUNCTION)
annotation class TargetClass(val value: String) {

}