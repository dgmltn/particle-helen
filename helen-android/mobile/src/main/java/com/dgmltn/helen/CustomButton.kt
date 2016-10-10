package com.dgmltn.helen

import android.content.Context
import android.content.Intent
import android.support.design.widget.FloatingActionButton
import android.util.AttributeSet
import android.view.HapticFeedbackConstants
import android.view.MotionEvent

/**
 * Created by dmelton on 10/9/16.
 */

class CustomButton(context: Context, attrs: AttributeSet) : FloatingActionButton(context, attrs) {

    var clickIntent : Intent? = null

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return when(ev.action) {
            MotionEvent.ACTION_DOWN -> {
                performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
                true
            }
            MotionEvent.ACTION_UP -> {
                context.startService(clickIntent)
                true
            }
            else -> false
        }
    }
}
