package com.example.pomodorotestapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        // Times and Button
        val pTime = findViewById<EditText>(R.id.pomodoro_time)
        val rTime = findViewById<EditText>(R.id.regeneration_time)
        val startBtn = findViewById<Button>(R.id.start_btn)

        startBtn.setOnClickListener {
            //check if time is valid
            if(timeIsValid(pTime.text.toString()) && timeIsValid(rTime.text.toString())) {
                //time is valid, so change the view
                val intent = Intent(this, TimeActivity::class.java).apply {
                    putExtra("PomodoroTime", pTime.text.toString())
                    putExtra("RegenerationTime", rTime.text.toString())
                }
                startActivity(intent)
            } else {
                //time is not valid
            }
        }


    }

    private fun timeIsValid(time: String): Boolean {


        return true
    }
}