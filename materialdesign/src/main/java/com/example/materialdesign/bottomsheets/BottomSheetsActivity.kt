package com.example.materialdesign.bottomsheets

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityBottomSheetsBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class BottomSheetsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBottomSheetsBinding
    private lateinit var behavior: BottomSheetBehavior<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomSheetsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        behavior = BottomSheetBehavior.from(binding.bottomSheet)
        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                Log.i("Zero", "newState: $newState")
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.i("Zero", "slideOffset: $slideOffset")
            }
        })

        binding.run {
            btnShow.setOnClickListener {
                if (behavior.state == BottomSheetBehavior.STATE_EXPANDED) behavior.state = BottomSheetBehavior.STATE_COLLAPSED
                else behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
            btnShow1.setOnClickListener {
                val mBottomSheetDialog = BottomSheetDialog(this@BottomSheetsActivity)
                val bt = layoutInflater.inflate(R.layout.dialog_bottom_sheet, null)
                mBottomSheetDialog.setContentView(bt)
                mBottomSheetDialog.show()
            }
        }
    }
}