package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding:ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = intent.getStringExtra(Constants.USER_NAME)
        binding.tvName.text = userName
        val correctAns = intent.getIntExtra(Constants.CORRECT_ANS,0)
        val totalQuestion = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)

        binding.tvScore.text = "Your score is $correctAns out of $totalQuestion"

        binding.btnFinish.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}