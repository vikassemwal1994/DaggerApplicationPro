package com.daggerapplication.textviews

import android.content.Context
import android.graphics.Typeface
import androidx.appcompat.widget.AppCompatTextView
import android.util.AttributeSet

class RegularTextView : AppCompatTextView {

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context) : super(context) {
        init()
    }


    fun init() {
        val tf = Typeface.createFromAsset(context.assets, "fonts/Metropolis_Regular.otf")
        typeface = tf
    }
}