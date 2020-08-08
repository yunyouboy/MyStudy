package com.example.materialdesign.drawer.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {
    private val mText: MutableLiveData<String> = MutableLiveData("This is gallery fragment")
    fun getText(): LiveData<String> {
        return mText
    }
}