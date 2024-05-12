package com.example.tap_the_koala

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tap_the_koala.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    // Function to start the Easy level
    fun StartEasy(view: View) {
        val intent = Intent(this, easy::class.java)
        startActivity(intent)
    }

    // Function to start the Medium level
    fun StartMedium(view: View) {
        val intent = Intent(this, medium::class.java)
        startActivity(intent)
    }

    // Function to start the Hard level
    fun StartHard(view: View) {
        val intent = Intent(this, hard::class.java)
        startActivity(intent)
    }

    // Function to restart the game
    fun Restart(view: View) {
        // For example, if you want to reset the game state and return to the main menu:
        // Reset any game-related variables or states here
        // Navigate back to the main menu
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        // You might also want to finish the current activity to clear it from the stack
        finish()
    }

    // Function to return to the main menu
    fun Reachmenu(view: View) {
        // For example, you might just want to navigate back to the main menu
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        // You might also want to finish the current activity to clear it from the stack
        finish()
    }
}
