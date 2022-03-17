package com.ded.mycaddog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ded.mycaddog.MainActivity
import com.ded.mycaddog.R


class MainChooseFragment : Fragment() {
    private lateinit var mainActivity: MainActivity
    private lateinit var cardCat: CardView
    private lateinit var cardDog: CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_choose, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        cardCat = view.findViewById(R.id.cardCat)
        cardDog = view.findViewById(R.id.cardDog)


        cardDog.setOnClickListener {
            var dogFragment = DogFragment()
            var fragmentManager: FragmentManager = mainActivity.supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.navContainer, dogFragment)
                .addToBackStack("name")
                .commit()
        }

        cardCat.setOnClickListener{
            var catFragment = CatFragment()
            var fragmentManager: FragmentManager = mainActivity.supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.navContainer, catFragment)
                .addToBackStack("name")
                .commit()
        }


    }
}