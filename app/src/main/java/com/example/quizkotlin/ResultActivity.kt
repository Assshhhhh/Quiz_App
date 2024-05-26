package com.example.quizkotlin

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.quizkotlin.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val byteArray = intent.getByteArrayExtra("image")
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)

        val username = intent?.getStringExtra("name")
        val score = intent.getStringExtra("score")
        val totalQuestions = intent.getStringExtra("total")


        if (score?.toInt()!!< 5) {
            binding.tvCongrats.text = "Better Luck Next Time " + username.toString() + "!\nBelow Average"
        }
        else if (score?.toInt() !! in 5..8){
            binding.tvCongrats.text = "That's Grape " + username.toString() + "!\nNot Bad"
        }
        else if (score?.toInt() in 9..10){
            binding.tvCongrats.text = "Congratulations " + username.toString() + "!\nYou've done excellent"
        }


        binding.tvScore.text = "${score} / ${totalQuestions.toString()}"
        binding.avatar.setImageBitmap(bitmap)
        binding.avatar.foreground = ContextCompat.getDrawable(this, R.drawable.avatar_no_stroke)

        binding.finishButton.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}