package com.dgmltn.helen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val btnLeftUp by lazy { findViewById(R.id.btn_left_up) as CustomButton }
    private val btnLeftMy by lazy { findViewById(R.id.btn_left_my) as CustomButton }
    private val btnLeftDown by lazy { findViewById(R.id.btn_left_down) as CustomButton }
    private val btnRightUp by lazy { findViewById(R.id.btn_right_up) as CustomButton }
    private val btnRightMy by lazy { findViewById(R.id.btn_right_my) as CustomButton }
    private val btnRightDown by lazy { findViewById(R.id.btn_right_down) as CustomButton }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLeftUp.clickIntent = ParticleHelenService.getLeftUpIntent(this)
        btnLeftMy.clickIntent = ParticleHelenService.getLeftMyIntent(this)
        btnLeftDown.clickIntent = ParticleHelenService.getLeftDownIntent(this)
        btnRightUp.clickIntent = ParticleHelenService.getRightUpIntent(this)
        btnRightMy.clickIntent = ParticleHelenService.getRightMyIntent(this)
        btnRightDown.clickIntent = ParticleHelenService.getRightDownIntent(this)
    }
}
