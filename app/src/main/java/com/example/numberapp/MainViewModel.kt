package com.example.numberapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import java.util.*

class MainViewModel : ViewModel() {
    private val mData = NumbersData.Base(OkHttpClient())
    private val mDate = GregorianCalendar().apply {
        timeInMillis = System.currentTimeMillis()
    }

    fun fetchFact(listener: OnFactFetchedListener) {
        viewModelScope.launch(Dispatchers.IO) {
            mData.fetchFact(mDate, listener)
        }
    }
}