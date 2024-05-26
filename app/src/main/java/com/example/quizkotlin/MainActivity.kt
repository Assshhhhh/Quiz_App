package com.example.quizkotlin

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import java.io.ByteArrayOutputStream
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.quizkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var isAvatarSelected: Boolean = false
    private var byteArray: ByteArray ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Hides Status Bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        setAvatarStyle()

        binding.maleAvatar.setOnClickListener {
            isAvatarSelected = true
            onAvatarSelectedStyle(binding.maleAvatar)

            sendImageToNextActivity(binding.maleAvatar.drawable)
        }

        binding.femaleAvatar.setOnClickListener {
            isAvatarSelected = true
            onAvatarSelectedStyle(binding.femaleAvatar)

            sendImageToNextActivity(binding.femaleAvatar.drawable)
        }

        binding.botAvatar.setOnClickListener {
            isAvatarSelected = true
            onAvatarSelectedStyle(binding.botAvatar)

            sendImageToNextActivity(binding.botAvatar.drawable)
        }

        binding.chipmunkAvatar.setOnClickListener {
            isAvatarSelected = true
            onAvatarSelectedStyle(binding.chipmunkAvatar)

            sendImageToNextActivity(binding.chipmunkAvatar.drawable)
        }

        binding.NextButton.setOnClickListener {

            val name: String = binding.input.text.toString().trim()
            val input_text: String = binding.input.text.toString().trim()

            if (input_text.isNotEmpty() && isAvatarSelected == true){
                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra("name", name)
                intent.putExtra("image", byteArray)
                startActivity(intent)
                finish()
            }
            else {
                Toast.makeText(this, "Enter Your Name and Select an Avatar", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun setAvatarStyle(){

        binding.maleAvatar.foreground = ContextCompat.getDrawable(this, R.drawable.avatar_stroke)
        binding.femaleAvatar.foreground = ContextCompat.getDrawable(this, R.drawable.avatar_stroke)
        binding.botAvatar.foreground = ContextCompat.getDrawable(this, R.drawable.avatar_stroke)
        binding.chipmunkAvatar.foreground = ContextCompat.getDrawable(this, R.drawable.avatar_stroke)

    }

    private fun onAvatarSelectedStyle(view:ImageView){
        setAvatarStyle()
        view.foreground = ContextCompat.getDrawable(this, R.drawable.avatar_stroke_selected)

    }

    private fun sendImageToNextActivity(drawable: Drawable){
        if (drawable is BitmapDrawable)
        {
            val bitmap = drawable.bitmap
            byteArray = bitmap.toByteArray()
        }
        else
        {
            // Handles the case where the drawable is not a BitmapDrawable
            Log.d("ImageSelection", "Drawable is not a BitmapDrawable")
        }

    }

}

    private fun Bitmap.toByteArray(): ByteArray {
        val outputStream = ByteArrayOutputStream()


        this.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }
