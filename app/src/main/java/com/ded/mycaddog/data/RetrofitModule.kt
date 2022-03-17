package com.ded.mycaddog.data

import com.ded.mycaddog.Config
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class RetrofitModule {
    fun createClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(30, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(30, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(
            object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request: Request.Builder = chain.request().newBuilder()
                    return chain.proceed(request.build())
                }
            }
        )
            .addInterceptor(logging)
        return okHttpClient.build()
    }

    fun createApiCat(): ApiCat? {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Config.CAT_URL)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiCat::class.java)
    }

    fun createApiDog(): ApiDog? {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Config.DOG_URL)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiDog::class.java)
    }
}