package com.ded.mycaddog.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.ded.mycaddog.MainActivity
import com.ded.mycaddog.R
import com.ded.mycaddog.data.MainVM


class CatFragment : Fragment() {
    lateinit var mainVM: MainVM
    lateinit var imgCat: ImageView
    lateinit var mainActivity: MainActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainVM = ViewModelProvider(this)[MainVM::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = activity as MainActivity
        imgCat = view.findViewById(R.id.imgCat)
        mainVM.getCatUrl(requireContext())
        mainActivity.progressBar.visibility = View.VISIBLE


        view.setOnClickListener{
            mainVM.getCatUrl(requireContext())
            mainActivity.progressBar.visibility = View.VISIBLE


        }
        mainVM.getMutableLiveData().observe(viewLifecycleOwner) { images ->

            imgCat.load(images)
            mainActivity.progressBar.visibility = View.INVISIBLE


        }
        mainVM.mutableImplLive.observe(viewLifecycleOwner) { isInternet ->

            if (!isInternet){
                mainActivity.createImageNotDownload()
            }


        }
    }
}
