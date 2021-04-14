package com.infinity.giphy.api

import com.infinity.giphy.model.random.RandomGif
import com.infinity.giphy.model.search.SearchGif
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GiphyApi {
    @GET("/v1/gifs/search?api_key=YGHnKKBGSydS6nSt6WAoUcICWwmgCfvL")
    suspend fun searchGif(@Query("q") q: String): Response<SearchGif?>?

    @GET("/v1/gifs/random?api_key=YGHnKKBGSydS6nSt6WAoUcICWwmgCfvL&limit=1")
    fun randomGif(): Call<RandomGif?>?
}