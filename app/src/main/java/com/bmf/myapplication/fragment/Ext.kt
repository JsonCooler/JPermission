package com.bmf.myapplication.fragment

object Ext {

    inline fun myName(sex: () -> Unit, crossinline name: () -> Unit) {
        sex()
        name()
    }

}