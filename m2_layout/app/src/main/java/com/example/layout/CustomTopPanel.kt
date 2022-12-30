package com.example.layout

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.layout.databinding.TopPanelBinding

class CustomTopPanel
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    val binding = TopPanelBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }

    fun changeTopText(text: String) {
        binding.topText.text = text
    }

    fun changeBottomText(text: String) {
        binding.bottomText.text = text
    }
}
