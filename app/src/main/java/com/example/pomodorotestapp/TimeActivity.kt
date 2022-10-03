package com.example.pomodorotestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ProgressBar
import android.widget.TextView

class TimeActivity : AppCompatActivity() {
    var minutes = 0
    var seconds = 0
    var pomodoroTimeFlag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)
    }

    override fun onResume() {
        super.onResume()

        // get values
        val pomodoroTimeParam = intent.getStringExtra("PomodoroTime")
        val regenarationTimeParam = intent.getStringExtra("RegenerationTime")
        val lableTimer = findViewById<TextView>(R.id.timerLable)
        val progressBar = findViewById<ProgressBar>(R.id.progress_timer)
        val timer = findViewById<TextView>(R.id.PomodoroTimeForTimer)

        timer.text = pomodoroTimeParam

        var timeInMillis = countDownTime(pomodoroTimeParam.toString())

        var progress = 0
        progressBar.isIndeterminate = false
        progressBar.max = (minutes * 60) + seconds

        // Countdown of the times
        object : CountDownTimer(timeInMillis, 1000) {
            override fun onTick(p0: Long) {
                timer.text = stringTime()

                //update countdown progress
                progress++
                progressBar.progress = progress
            }

            override fun onFinish() {
                progress = 0
                progressBar.progress = progress
                if(pomodoroTimeFlag) {
                    pomodoroTimeFlag = false
                    timeInMillis = countDownTime(regenarationTimeParam.toString())
                    timer.text = regenarationTimeParam
                } else {
                    pomodoroTimeFlag = true
                    timeInMillis = countDownTime(pomodoroTimeParam.toString())
                    timer.text = pomodoroTimeParam
                }
                progressBar.max = (minutes * 60) + seconds
                start()

            }
        }.start()

    }

    fun countDownTime (time: String): Long {
        val timeParts = time.split(":")
        var countDownTimeInMillis =  (timeParts[0].toLong() * 60000) + (timeParts[1].toLong() * 1000)

        minutes = timeParts[0].toInt()
        seconds = timeParts[1].toInt()

        return countDownTimeInMillis
    }

    fun stringTime (): String {
        if(seconds == 0) {
            seconds = 59
            if(minutes != 0)
                minutes--
        } else
            seconds--

        return "$minutes:$seconds"
    }

}