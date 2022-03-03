package com.example.numberapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity(), OnFactFetchedListener {
    private lateinit var mViewModel: MainViewModel
    private lateinit var mContent: ViewGroup
    private lateinit var mLoading: ViewGroup
    private lateinit var mError: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]
        mContent = findViewById(R.id.mainContent)
        mLoading = findViewById(R.id.loading)
        mError = findViewById(R.id.noInternet)
        startLoading()
        findViewById<Button>(R.id.nextFact).setOnClickListener {
            startLoading()
        }
    }

    override fun onFetched(fact: DateFact) {
        runOnUiThread {
            mLoading.visibility = View.GONE
            mContent.visibility = View.VISIBLE
            mError.visibility = View.GONE
            fact.map(object : DateFact.Mapper<Unit> {
                override fun map(fact: String) {
                    mContent.findViewById<TextView>(R.id.textFact).text = fact
                }
            })
        }
    }

    override fun onError() {
        runOnUiThread {
            mLoading.visibility = View.GONE
            mContent.visibility = View.GONE
            mError.visibility = View.VISIBLE
        }
    }

    private fun startLoading() {
        mViewModel.fetchFact(this)
        mLoading.visibility = View.VISIBLE
        mContent.visibility = View.GONE
        mError.visibility = View.GONE
    }
}