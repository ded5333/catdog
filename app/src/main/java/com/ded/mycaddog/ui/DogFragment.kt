package com.ded.mycaddog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.ded.mycaddog.MainActivity
import com.ded.mycaddog.R
import com.ded.mycaddog.data.MainVM


class DogFragment : Fragment() {

    lateinit var mainVM: MainVM
    lateinit var imgDog: ImageView
    lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainVM = ViewModelProvider(this)[MainVM::class.java]
        mainActivity = activity as MainActivity

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dog, container, false)

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        imgDog = view.findViewById(R.id.imgDog)
        mainVM.getDogUrl(requireContext())
        mainActivity.progressBar.visibility = View.VISIBLE

        view.setOnClickListener {
            mainVM.getDogUrl(requireContext())
            mainActivity.progressBar.visibility = View.VISIBLE

        }

        mainVM.getMutableLiveData().observe(viewLifecycleOwner) { url ->
            imgDog.load(url)
            mainActivity.progressBar.visibility = View.INVISIBLE
        }
        mainVM.mutableImplLive.observe(viewLifecycleOwner) { isInternet ->

            if (!isInternet) {
                mainActivity.createImageNotDownload()
            }
        }

    }
}