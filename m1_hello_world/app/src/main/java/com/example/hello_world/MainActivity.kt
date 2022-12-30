package com.example.hello_world

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hello_world.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var counter = 0

        binding.printMessage.text = "Все места свободны"
        binding.printMessage.setTextColor(Color.rgb(255, 255, 255))

        binding.buttonPlus.setOnClickListener {

            if (counter == 49) {
                counter++
                binding.printMessage.text = "Все места заняты"
                binding.counterOfPassenger.text = counter.toString()
                binding.printMessage.setTextColor(Color.rgb(255, 0, 0))
            } else if (counter < 49) {
                counter++
                binding.printMessage.text = "Осталось мест: ${50 - counter}"
                binding.printMessage.setTextColor(Color.rgb(0, 0, 255))
                binding.counterOfPassenger.text = counter.toString()
            }
        }

        binding.buttonMinus.setOnClickListener {

            if (counter == 0) {
                binding.printMessage.text = "Все места свободны"
                binding.printMessage.setTextColor(Color.rgb(255, 255, 255))
                binding.counterOfPassenger.text = counter.toString()
            } else if (counter > 0) {
                counter--
                binding.printMessage.text = "Осталось мест: ${50 - counter}"
                binding.printMessage.setTextColor(Color.rgb(0, 0, 255))
                binding.counterOfPassenger.text = counter.toString()
            }
        }

        binding.resetButton.setOnClickListener {

            counter = 0
            binding.printMessage.text = "Все места свободны"
            binding.printMessage.setTextColor(Color.rgb(0, 255, 0))
            binding.counterOfPassenger.text = counter.toString()

        }

    }
}