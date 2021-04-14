package com.infinity.giphy.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder private constructor() {
    private val mRetrofit: Retrofit

    companion object {
        private var mInstance: RetrofitBuilder? = null
        private const val BASE_URL = "https://api.giphy.com"
        val instance: RetrofitBuilder?
            get() {
                if (mInstance == null) {
                    mInstance = RetrofitBuilder()
                }
                return mInstance
            }
    }

    init {
        mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    fun getAPI(): GiphyApi? {
        return mRetrofit.create(GiphyApi::class.java)
    }
}