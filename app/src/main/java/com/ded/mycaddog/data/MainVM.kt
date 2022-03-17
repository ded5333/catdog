package com.ded.mycaddog.data

import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ded.mycaddog.App
import com.ded.mycaddog.data.model.Cat
import com.ded.mycaddog.data.model.Dog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainVM : ViewModel() {
    private val scope = CoroutineScope(Dispatchers.IO)

    private val _mutableLiveData = MutableLiveData<String>()
    fun getMutableLiveData(): LiveData<String> {
        return _mutableLiveData
    }

    private val _mutableImplLive = MutableLiveData<Boolean>()
    var mutableImplLive: LiveData<Boolean> = _mutableImplLive

    var isLoaded = false

    fun getDogUrl(context: Context) {
        scope.launch {
            if (!checkForInternet(context)) {
                _mutableImplLive.postValue(false)
                return@launch
            }
            var imgDogs = App.api_dog.getDog()
            imgDogs.enqueue(object : Callback<Dog> {
                override fun onResponse(call: Call<Dog>, response: Response<Dog>) {
                    val dog: Dog? = response.body()
                    val dogUrl = dog?.message

                    _mutableLiveData.value = dogUrl
                    _mutableImplLive.postValue(true)
                    isLoaded = true

                }

                override fun onFailure(call: Call<Dog>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })


        }


    }

    fun getCatUrl(context: Context) {

        if (checkForInternet(context)) {


            var imgCat = App.api_cat.getCat()
            imgCat.enqueue(object : Callback<List<Cat>> {
                override fun onResponse(call: Call<List<Cat>>, response: Response<List<Cat>>) {
                    var cat: List<Cat>? = response.body()
                    var catUrl = cat?.get(0)?.url
                    _mutableLiveData.value = catUrl
                    _mutableImplLive.postValue(true)

                }

                override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
                }

            })
        } else
            _mutableImplLive.postValue(false)
    }

    private fun checkForInternet(context: Context): Boolean {

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}