package com.bingo.aspect

import javax.xml.transform.OutputKeys.METHOD


@Retention(AnnotationRetention.RUNTIME)
@Target( AnnotationTarget.TYPE, AnnotationTarget.FUNCTION)
annotation class TargetClass {
}