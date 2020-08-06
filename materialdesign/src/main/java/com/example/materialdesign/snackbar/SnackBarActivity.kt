package com.example.materialdesign.snackbar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.databinding.ActivitySnackBarBinding
import com.google.android.material.snackbar.Snackbar

class SnackBarActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySnackBarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySnackBarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSnackBar.setOnClickListener {
            Snackbar.make(binding.btnSnackBar, "Hello SnackBar!", Snackbar.LENGTH_LONG).setAction("SnackBarTest") {
                // do something
            }.show()
        }
    }
}