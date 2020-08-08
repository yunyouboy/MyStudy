package com.example.materialdesign.materialtext

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.databinding.ActivityTextInputBinding
import com.google.android.material.textfield.TextInputLayout

class TextInputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTextInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.materialbtn1.setOnClickListener { showError(binding.textInput, "错误展示！！！") }
    }

    private fun showError(textInputLayout: TextInputLayout, error: String?) {
        textInputLayout.error = error
        textInputLayout.editText?.isFocusable = true
        textInputLayout.editText?.isFocusableInTouchMode = true
        textInputLayout.editText?.requestFocus()
    }
}