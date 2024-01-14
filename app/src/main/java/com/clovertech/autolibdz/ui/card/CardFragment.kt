package com.clovertech.autolibdz.ui.card

import ViewModel.ViewModelCard
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.clovertech.autolibdz.api.CardsApi
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ViewModel.ViewModelCards
import com.clovertech.autolibdz.ViewModel.ViewModelCardsFactory
import com.clovertech.autolibdz.repository.CardsRepository
import kotlinx.android.synthetic.main.fragment_card.*


class CardFragment : Fragment() {


    private lateinit var cardViewModel: CardViewModel
    private lateinit var viewModel: ViewModelCards
    private lateinit var cardFactory:ViewModelCardsFactory

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cardViewModel =
            ViewModelProvider(this).get(CardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_card, container, false)

        return root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val amount=arguments?.getInt("amount").toString()
        val id=arguments?.getInt("idrental").toString()
        val idcar=arguments?.getInt("idcar")
        val add_card_fragment = AddCardFragment()
        Toast.makeText(context,"amount to pay $amount", Toast.LENGTH_LONG).show()
        val fragmentManager = (activity as FragmentActivity).supportFragmentManager
        add_card.setOnClickListener {
            add_card_fragment.show(fragmentManager,"add_card_fragment")
            loadCard(fragmentManager,amount,id)


        }
        loadCard(fragmentManager,amount,id)


    }

    private fun loadCard(fragmentManager: FragmentManager,amount:String,id:String) {
        val cardApi= CardsApi()
        val repository=CardsRepository(cardApi)
        cardFactory=ViewModelCardsFactory(repository)
        viewModel=ViewModelProvider(this,cardFactory).get(ViewModelCards::class.java)

        viewModel.getCards()
        viewModel.userCards.observe(viewLifecycleOwner, Observer { cardsList->
            list_card.also {
                it.layoutManager=LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter= CardAdapter(requireContext(),cardsList,fragmentManager,amount,id)

            }
        })
    }


}