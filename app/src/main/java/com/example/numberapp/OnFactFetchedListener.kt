package com.example.numberapp

interface OnFactFetchedListener {
    fun onFetched(fact: DateFact)

    fun onError()
}