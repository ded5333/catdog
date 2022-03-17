package com.ded.mycaddog

import android.app.Application
import com.ded.mycaddog.data.ApiCat
import com.ded.mycaddog.data.ApiDog
import com.ded.mycaddog.data.RetrofitModule

class App: Application() {
    companion object{
        lateinit var api_dog: ApiDog
        lateinit var api_cat: ApiCat
        var instance: App? = null

    }
    override fun onCreate() {
        super.onCreate()
        if (instance == null) {
            instance = this
        }

        api_cat = RetrofitModule().createApiCat()!!
        api_dog = RetrofitModule().createApiDog()!!

    }
}