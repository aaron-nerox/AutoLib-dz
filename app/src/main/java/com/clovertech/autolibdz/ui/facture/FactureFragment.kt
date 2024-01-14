package com.clovertech.autolibdz.ui.facture

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.clovertech.autolibdz.api.FactureApi
import com.clovertech.autolibdz.Adapters.BillAdapter
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ViewModel.BillViewModel
import com.clovertech.autolibdz.ViewModel.BillViewModelFactory
import com.clovertech.autolibdz.repository.FactureRepository
import kotlinx.android.synthetic.main.fragment_facture.*

class FactureFragment :Fragment(){
    private lateinit var factureViewModel: FactureViewModel
    private lateinit var billViewModel:BillViewModel
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
        val api= FactureApi()
        val repository=FactureRepository(api)
        val factory=BillViewModelFactory(repository)
        billViewModel=ViewModelProvider(this,factory).get(BillViewModel::class.java)
        billViewModel.getFact()
        billViewModel.facts.observe(viewLifecycleOwner, Observer { factList->
            list_facture.also {
                it.layoutManager=LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter= BillAdapter(requireContext(),factList)
            }
        })
    }

}
/*


class FactureFragment : Fragment() {

    private lateinit var factureViewModel: FactureViewModel
    private lateinit var viewModelfact:BillViewModel
    private lateinit var factoryBill:FactureViewModel


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

      /*  list_facture.apply {
            list_facture.layoutManager = LinearLayoutManager(activity)
            list_facture.adapter = FactureAdapter(context, loadData())
        }*/
        val factureApi= FactureApi()
        val com.clovertech.autolibdz.repository= FactureRepository(factureApi)
        factoryBill=BillViewModelFactory(com.clovertech.autolibdz.repository)
        viewModelfact=ViewModelProvider(this,factoryBill).get(BillViewModel::class.java)
        viewModelfact.getFact()
        viewModelfact.facts.observe(viewLifecycleOwner, Observer { factList->
            list_facture.also {
                it.layoutManager=LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter= FactureAdapter(requireContext(),factList)
            }
        })
    }

}
* */