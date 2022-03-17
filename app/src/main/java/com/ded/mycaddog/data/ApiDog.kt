package com.ded.mycaddog.data

import com.ded.mycaddog.data.model.Dog
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiDog {
    @GET("breeds/image/random")
    fun getDog(): Call<Dog>
}