package me.learn.blogmultiplatformshofwan.utils

import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint


fun Breakpoint.smallerThanSM(): Boolean {
    return this < Breakpoint.SM
}

fun Breakpoint.largerThanMD(): Boolean {
    return this > Breakpoint.MD
}