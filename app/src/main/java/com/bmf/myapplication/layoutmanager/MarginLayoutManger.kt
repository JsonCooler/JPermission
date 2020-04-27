package com.bmf.myapplication.layoutmanager

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager

class MarginLayoutManger(context: Context) : LinearLayoutManager(context) {
    override fun calculateItemDecorationsForChild(child: View, outRect: Rect) {
        outRect.set(20, 20, 20, 20)
    }


}