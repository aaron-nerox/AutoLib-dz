package com.clovertech.autolibdz.ui.subscription

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.clovertech.autolibdz.R
import kotlinx.android.synthetic.main.fragment_subscription.*

class SubscriptionFragment  : Fragment() {


    override fun onCreateView(

            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_subscription, container, false)

        return root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val add_sub_fragment= AddSubFragment()
        val fragmentManager = (activity as FragmentActivity).supportFragmentManager


        create.setOnClickListener {
            add_sub_fragment.show(fragmentManager, "add_sub_fragment") //show add card fragment
        }


    }


}
