package com.bmf.myapplication

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.noober.background.BackgroundFactory

class ShadowTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {
    init {
        BackgroundFactory.setViewBackground(context, attrs, this)
    }
}