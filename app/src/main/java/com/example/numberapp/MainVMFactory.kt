package com.example.numberapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainVMFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel() as T
    }
}