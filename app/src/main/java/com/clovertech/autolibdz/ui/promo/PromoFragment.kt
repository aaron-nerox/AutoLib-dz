package com.clovertech.autolibdz.ui.promo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import api.PromoApi
import com.clovertech.autolibdz.Adapters.PromoAdapter
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ViewModel.*
import com.clovertech.autolibdz.repository.PromoRepository
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_promo.*
import kotlinx.android.synthetic.main.fragment_promo.close
import kotlinx.android.synthetic.main.tarification.*


class PromoFragment : BottomSheetDialogFragment() {

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


        close.setOnClickListener{
            this.dismiss()
        }
        var priceReduHelper=price
        priceReduHelper.setText("0 DA")
        var totalprice= arguments?.getInt("totalprice")

        val api= PromoApi()
        val repository= PromoRepository(api)
        val promo= PromoViewModelFactory(repository)
        promoCodeViewModel=ViewModelProvider(this,promo).get(PromoCodeViewModel::class.java)
        promoCodeViewModel.getPromo()
        promoCodeViewModel.promo.observe(viewLifecycleOwner, Observer { promoList->
          list_promo.also {
                it.layoutManager=LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter= totalprice?.let { it1 ->
                    PromoAdapter(requireContext(),promoList,priceReduHelper,
                        it1
                    )
                }
            }
        })
        confirm.setOnClickListener {
            val newprice= pricetotarif
            listner=true
            requireActivity().total.setText(newprice.toString()+" DA")
            this.dismiss()

        }



    }


}