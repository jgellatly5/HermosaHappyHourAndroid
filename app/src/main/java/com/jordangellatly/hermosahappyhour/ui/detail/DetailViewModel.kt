package com.jordangellatly.hermosahappyhour.ui.detail

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel : ViewModel() {

    private var timer: CountDownTimer? = null
    private var isFinished = true

    val timerCountDown = MutableLiveData<Long>()

    fun startNewTimer() {
        if (isFinished) {
            timer?.cancel()
            timer = object : CountDownTimer(10000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    isFinished = false
                    timerCountDown.value = millisUntilFinished
                }

                override fun onFinish() {
                    isFinished = true
                    startNewTimer()
                }
            }.start()
        }
    }
}