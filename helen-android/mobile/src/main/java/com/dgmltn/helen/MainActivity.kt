package com.dgmltn.helen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.MotionEvent

class MainActivity : AppCompatActivity() {

    private val btnLeftUp by lazy { findViewById(R.id.btn_left_up) }
    private val btnLeftMy by lazy { findViewById(R.id.btn_left_my) }
    private val btnLeftDown by lazy { findViewById(R.id.btn_left_down) }
    private val btnRightUp by lazy { findViewById(R.id.btn_right_up) }
    private val btnRightMy by lazy { findViewById(R.id.btn_right_my) }
    private val btnRightDown by lazy { findViewById(R.id.btn_right_down) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLeftUp.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    btnLeftUp.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
                    true
                }
                MotionEvent.ACTION_UP -> {
                    startService(ParticleHelenService.getLeftUpIntent(this))
                    true
                }
                else -> false
            }
        }

        btnLeftMy.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    btnLeftUp.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
                    true
                }
                MotionEvent.ACTION_UP -> {
                    startService(ParticleHelenService.getLeftMyIntent(this))
                    true
                }
                else -> false
            }
        }

        btnLeftDown.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    btnLeftUp.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
                    true
                }
                MotionEvent.ACTION_UP -> {
                    startService(ParticleHelenService.getLeftDownIntent(this))
                    true
                }
                else -> false
            }
        }

        btnRightUp.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    btnLeftUp.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
                    true
                }
                MotionEvent.ACTION_UP -> {
                    startService(ParticleHelenService.getRightUpIntent(this))
                    true
                }
                else -> false
            }
        }

        btnRightMy.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    btnLeftUp.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
                    true
                }
                MotionEvent.ACTION_UP -> {
                    startService(ParticleHelenService.getRightMyIntent(this))
                    true
                }
                else -> false
            }
        }

        btnRightDown.setOnTouchListener { view, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    btnLeftUp.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
                    true
                }
                MotionEvent.ACTION_UP -> {
                    startService(ParticleHelenService.getRightDownIntent(this))
                    true
                }
                else -> false
            }
        }
    }
}
