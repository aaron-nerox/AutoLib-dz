package com.clovertech.autolibdz.ui.tarification

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.clovertech.autolibdz.DataClasses.Rental
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ViewModel.RentalViewModel
import com.clovertech.autolibdz.ViewModel.RentalViewModelFactory
import com.clovertech.autolibdz.repository.RentalRepository
import com.clovertech.autolibdz.ui.card.ConfirmPayFragment
import com.clovertech.autolibdz.ui.promo.PromoFragment
import com.clovertech.autolibdz.ui.promo.listner
import com.clovertech.autolibdz.ui.promo.pricetotarif
import kotlinx.android.synthetic.main.fragment_promo.*
import kotlinx.android.synthetic.main.tarification.*
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


class TarificationFragment : Fragment(){
    private lateinit var tarificationViewModel: TarificationViewModel
    private lateinit var rentalViewModel: RentalViewModel
    val typepaiement = arrayOf("Jour", "Heur")
    val cardslist = arrayOf("Credit Card", "Carte d'abonnement")
    var days = 0
    var totalprice = 0
    var idrental=-1


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        tarificationViewModel =
                ViewModelProvider(this).get(TarificationViewModel::class.java)
        val root = inflater.inflate(R.layout.tarification, container, false)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        total.text="0DA"
        val id=arguments?.getInt("id")
        val mod=arguments?.getString("model")
        model.text=mod
        val img=arguments?.getString("img")
        Glide.with(context).load(img).into(img_v)
        val uni_hr=arguments?.getInt("hr")
        val uni_jr=arguments?.getInt("jr")
        var sub=false
        brand.text=arguments?.getString("brand")
        val repository = RentalRepository()
        val factory = RentalViewModelFactory(repository)
        rentalViewModel = ViewModelProvider(this,factory)
                .get(RentalViewModel::class.java)
        plus.setOnClickListener {
            var d:Int=  duree.text.toString().toInt()
            d += 1
            days=d
            duree.setText(d.toString())
            totalprice=(d* uni_jr!!)
            total.setText(totalprice.toString()+" DA")

        }
        moins.setOnClickListener {
            var d:Int=  duree.text.toString().toInt()
            if(d-1>=0){
                d -= 1
                days=d
                totalprice=(d* uni_jr!!)
                duree.setText(d.toString())
                total.setText(totalprice.toString()+" DA")
            }
            else{
                Toast.makeText(activity,"Vous pouvez pas Avoir une durée < 0",Toast.LENGTH_SHORT).show()
            }}

        code_promo.setOnClickListener {

            val fragmentManager = (activity as FragmentActivity).supportFragmentManager
            val promoFragment = PromoFragment()
            val args= bundleOf("totalprice" to totalprice)
                promoFragment.arguments=args
          promoFragment.show(fragmentManager, "promo_fragment")

        }
        pay.setOnClickListener{
            val date_time= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
                        .withZone(ZoneOffset.UTC)
                        .format(Instant.now())
            } else {
                TODO("VERSION.SDK_INT < O")
            }
            val t=LocalTime.now()
            val d= LocalDate.now()
            Toast.makeText(context,"id $id",Toast.LENGTH_LONG).show()
            val rental=
                    id?.let {
                        Rental(0,11, it,date_time,LocalTime.now().toString(),d.plusDays(2).toString()+" "+t.toString(),
                                t.toString(),d.plusDays(2).toString()+" "+t.toString(),t.toString(),"jour",
                                1,1,"active")
                    }


            rental?.let { rentalViewModel.addRental(it) }
            rentalViewModel.rentalResponse
                    .observe(viewLifecycleOwner, Observer {
                        response ->

                        if (response.isSuccessful){
                            idrental= response.body()!!.idRental


                            Log.e("Push",response.body().toString())
                            Log.e("Push",response.code().toString())
                            if (listner)
                            {
                                val bundle = bundleOf("amount" to pricetotarif,"idrental" to idrental)
                                Toast.makeText(requireContext(),"to card :$pricetotarif",Toast.LENGTH_SHORT).show()
                                view?.findNavController()?.navigate(R.id.action_nav_slideshow_to_nav_card,bundle)


                            }
                            sub=true
                            if (sub)
                            {
                                val bundle = bundleOf("idTenant" to 11,"amount" to totalprice)
                                Toast.makeText(requireContext(),"to card :$pricetotarif",Toast.LENGTH_SHORT).show()
                                view?.findNavController()?.navigate(R.id.action_nav_slideshow_to_nav_sub,bundle)


                            }

                            else{
                                val bundle = bundleOf("amount" to totalprice,"idrental" to idrental)
                                Toast.makeText(requireContext(),"to card :$totalprice",Toast.LENGTH_SHORT).show()
                                view?.findNavController()?.navigate(R.id.action_nav_slideshow_to_nav_card,bundle)
                            }

                            Toast.makeText(requireContext(),"rental added successfully",Toast.LENGTH_SHORT).show()
                        }else{
                            Log.e("Push",response.body().toString())
                            Log.e("Push",response.code().toString())
                            Toast.makeText(requireContext(),"erreur rental not added ",Toast.LENGTH_SHORT).show()
                        }
                    })


        }



    }
}
