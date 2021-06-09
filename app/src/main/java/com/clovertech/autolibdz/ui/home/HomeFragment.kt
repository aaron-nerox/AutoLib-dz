package com.clovertech.autolibdz.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolibdz.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.tarification, container, false)
     //   val textView: TextView = root.findViewById(R.id.text_home)

       /* homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
       /* val types = arrayOf("simple User", "Admin")
        val t=inflater.inflate(R.layout.fragment_tarification, container, false)
        val type = t.findViewById<Spinner>(R.id.type)
        type?.adapter = activity?.let { ArrayAdapter(it, R.layout.fragment_tarification,types) } as SpinnerAdapter
        type?.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("erreur")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val type = parent?.getItemAtPosition(position).toString()
                println(type)
            }
        }*/
        return root
    }
}