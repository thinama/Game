package com.example.tap_the_koala

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.tap_the_koala.MainActivity
import com.example.tap_the_koala.R
import com.example.tap_the_koala.databinding.ActivityEasyBinding

class hard : AppCompatActivity() {
    private lateinit var binding: ActivityEasyBinding
    private var score: Int = 0
    private val imagePosition = ArrayList<ImageView>()
    private val handler = Handler()
    private lateinit var runnable: Runnable
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEasyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize shared preferences
        sharedPreferences = getSharedPreferences("MyGamePrefs", MODE_PRIVATE)

        score = 0
        imagePosition.addAll(listOf(
            binding.imageView1, binding.imageView2, binding.imageView3,
            binding.imageView4, binding.imageView5, binding.imageView6,
            binding.imageView7, binding.imageView8, binding.imageView9,
            binding.imageView10, binding.imageView11, binding.imageView12
        ))

        makeInvisible()

        object : CountDownTimer(15000, 500) {
            override fun onFinish() {
                binding.timeid.text = "GAME OVER!!"
                handler.removeCallbacks(runnable)
                imagePosition.forEach { it.visibility = View.INVISIBLE }
                showGameOverLayout()
            }

            override fun onTick(p0: Long) {
                binding.timeid.text = "TIMER:" + p0 / 1000
            }
        }.start()
    }

    private fun makeInvisible() {
        runnable = Runnable {
            imagePosition.forEach { it.visibility = View.INVISIBLE }
            val curIndex = kotlin.random.Random.nextInt(11 - 0)
            imagePosition[curIndex].visibility = View.VISIBLE
            handler.postDelayed(runnable, 500)
        }
        handler.post(runnable)
    }

    fun increaseScore(view: View) {
        score += 10

        val sharedPreferences = getSharedPreferences("MyGamePrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("PlayerScore", score)
        editor.apply()
    }

    private fun showGameOverLayout() {
        setContentView(R.layout.activity_hard_score)

        // Display the score
        val scoreTextView = findViewById<TextView>(R.id.scoreid)
        scoreTextView.text = "SCORE: $score"

        // Retrieve highest score from shared preferences
        val highestScore = sharedPreferences.getInt("HighestScore", 0)

        // Compare and update highest score
        if (score > highestScore) {
            val editor = sharedPreferences.edit()
            editor.putInt("HighestScore", score)
            editor.apply()
        }

        // Display highest score
        val highestScoreTextView = findViewById<TextView>(R.id.highscoreid)
        highestScoreTextView.text = "HIGHEST SCORE: $highestScore"

        // Handle return home button click
        val returnHomeButton = findViewById<Button>(R.id.button4)
        returnHomeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Handle restart button click
        val restartButton = findViewById<Button>(R.id.restartid)
        restartButton.setOnClickListener {
            score = 0
            binding.timeid.text = "TIME: 20"
            finish()
            startActivity(intent)
        }
    }
}
