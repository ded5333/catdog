package com.ded.mycaddog.data

import com.ded.mycaddog.data.model.Cat
import retrofit2.Call
import retrofit2.http.GET

interface ApiCat {
    @GET("images/search")
    fun getCat(): Call<List<Cat>>
}