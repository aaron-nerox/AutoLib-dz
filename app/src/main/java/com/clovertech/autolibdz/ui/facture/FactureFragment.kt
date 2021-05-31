package com.clovertech.autolibdz.ui.facture

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.clovertech.autolibdz.R
import kotlinx.android.synthetic.main.fragment_facture.*


class FactureFragment : Fragment() {

    private lateinit var factureViewModel: FactureViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        factureViewModel =
            ViewModelProvider(this).get(FactureViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_facture, container, false)

        factureViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        list_facture.apply {
            list_facture.layoutManager = LinearLayoutManager(activity)
            list_facture.adapter = FactureAdapter(context,loadData())
        }
    }
    fun loadData():List<Facture> {
        val data = mutableListOf<Facture>()
        data.add(Facture(1287654,"08/03/2021","Chéraga,Algiers",R.drawable.ccp_logo))
        data.add(Facture(1287654,"08/03/2021","Chéraga,Algiers",R.drawable.ccp_logo))
        data.add(Facture(1287654,"08/03/2021","Chéraga,Algiers",R.drawable.ccp_logo))
        data.add(Facture(1287654,"08/03/2021","Chéraga,Algiers",R.drawable.ccp_logo))
        data.add(Facture(1287654,"08/03/2021","Chéraga,Algiers",R.drawable.ccp_logo))
        data.add(Facture(1287654,"08/03/2021","Chéraga,Algiers",R.drawable.ccp_logo))
        data.add(Facture(1287654,"08/03/2021","Chéraga,Algiers",R.drawable.ccp_logo))
        return data

    }
}