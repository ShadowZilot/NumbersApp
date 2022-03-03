package com.example.numberapp

import androidx.annotation.StringRes

sealed interface FactResult {



    class Success(
        private val mFact: DateFact
    ) : FactResult {

    }

    class Fail(
        @StringRes private val mMessage: Int
    ) : FactResult {

    }
}