package com.example.numberapp

import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*

interface NumbersData {

    fun fetchFact(date: Calendar, listener: OnFactFetchedListener)

    class Base(
        private val mClient: OkHttpClient
    ) : NumbersData {

        override fun fetchFact(date: Calendar, listener: OnFactFetchedListener) {
            val request = Request.Builder()
                .url("http://numbersapi.com/${date.get(Calendar.MONTH)}/${date.get(Calendar.DAY_OF_MONTH)}/date?json")
                .build()
            mClient.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    listener.onError()
                }

                override fun onResponse(call: Call, response: Response) {
                    try {
                        listener.onFetched(
                            DateFact(
                                JSONObject(response.body!!.string())
                            )
                        )
                    } catch (e: JSONException) {
                        listener.onError()
                    }
                }
            })
        }
    }
}