package com.example.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.layout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.customTopPanel.changeTopText("Тут был текст")
        binding.customTopPanel.changeBottomText("Тут был текст очень длинный текст, который был сокращен")
    }
}