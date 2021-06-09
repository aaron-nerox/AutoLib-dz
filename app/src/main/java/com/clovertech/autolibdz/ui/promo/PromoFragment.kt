package com.clovertech.autolibdz.ui.promo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.clovertech.autolibdz.APIs.PromoApi
import com.clovertech.autolibdz.Adapters.PromoAdapter
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ViewModel.*
import com.clovertech.autolibdz.repository.PromoRepository
import kotlinx.android.synthetic.main.fragment_promo.*


class PromoFragment : Fragment() {

    private lateinit var promoViewModel: PromoViewModel
    private lateinit var promoCodeViewModel: PromoCodeViewModel
    private lateinit var promoViewModelFactory: PromoViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        promoViewModel =
            ViewModelProvider(this).get(PromoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_promo, container, false)

        promoViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api= PromoApi()
        val repository= PromoRepository(api)
        val promo= PromoViewModelFactory(repository)
        promoCodeViewModel=ViewModelProvider(this,promo).get(PromoCodeViewModel::class.java)
        promoCodeViewModel.getPromo()
        promoCodeViewModel.promo.observe(viewLifecycleOwner, Observer { promoList->
            list_promo.also {
                it.layoutManager=LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter= PromoAdapter(requireContext(),promoList)
            }
        })
    }


}