package com.example.numberapp

import org.json.JSONObject

data class DateFact(
    private val mFact: String
) {
    constructor(item: JSONObject): this(
        item.getString("text")
    )

    fun <T> map(
        mapper: Mapper<T>
    ): T {
        return mapper.map(mFact)
    }

    interface Mapper<T> {
        fun map(
            fact: String
        ): T
    }
}
