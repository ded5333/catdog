package com.ded.mycaddog

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.ded.mycaddog.data.DialogUtils
import com.ded.mycaddog.ui.MainChooseFragment
import com.google.android.gms.ads.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAdView: AdView
    lateinit var progressBar:ProgressBar

    companion object{

        var fragmentCreated = false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        // MobileAds.initialize(this);
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        mAdView = findViewById(R.id.adView)
        progressBar = findViewById(R.id.progBar)

        val manager = supportFragmentManager
        if (!fragmentCreated){

        val mainFragment = MainChooseFragment()

        var transaction = manager.beginTransaction()
        transaction.replace(R.id.navContainer, mainFragment)
        transaction.commit()
            fragmentCreated = true
        }

        initBanner()



    }




    fun initBanner(){
        MobileAds.initialize(this) {
            val adView = AdView(this@MainActivity)
            //ca-app-pub-4433493293018663/9700347467
            if (BuildConfig.DEBUG) adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"
            val adRequest = AdRequest.Builder().build()
            mAdView.loadAd(adRequest)
            mAdView.setAdListener(object : AdListener() {
                override fun onAdLoaded() {
                    // Code to be executed when an ad finishes loading.
                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    // Code to be executed when an ad request fails.
                }

                override fun onAdOpened() {
                    // Code to be executed when an ad opens an overlay that
                    // covers the screen.
                }

                override fun onAdClicked() {
                    // Code to be executed when the user clicks on an ad.
                }

                override fun onAdClosed() {
                    // Code to be executed when the user is about to return
                    // to the app after tapping on an ad.
                }
            })
        }

    }
    fun createImageNotDownload() {

        DialogUtils.showDialog(
            this,
            getString(R.string.Connection),
            getString(R.string.check),
            getString(R.string.ok),

            yesClickListener = { dialog, which ->
                dialog.dismiss()
            },

            )
    }
}