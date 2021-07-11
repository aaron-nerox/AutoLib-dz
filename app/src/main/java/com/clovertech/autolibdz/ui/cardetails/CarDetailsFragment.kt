package com.clovertech.autolibdz.ui.cardetails

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.clovertech.autolibdz.R
import com.clovertech.autolibdz.ViewModel.RentalViewModel
import com.clovertech.autolibdz.ViewModel.RentalViewModelFactory
import com.clovertech.autolibdz.ViewModel.ViewModelCars
import com.clovertech.autolibdz.repository.RentalRepository
import kotlinx.android.synthetic.main.fragment_car_details.*
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class CarDetailsFragment : Fragment() {


    private lateinit var carDetailViewModel: CarDetailsViewModel
    private lateinit var rentalViewModel: RentalViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        carDetailViewModel =
                ViewModelProvider(this).get(CarDetailsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_car_details, container, false)

        return root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val idcar=arguments?.getInt("id")
        val model=arguments?.getString("model")
        car.text=model
        val img=arguments?.getString("img")
        Glide.with(requireContext()).load(img).into(img_car)
        val uni_hr=arguments?.getInt("hr")
        val uni_jr=arguments?.getInt("jr")
        val brand=arguments?.getString("brand")
         val matricule=arguments?.getString("matricule")
        val color=arguments?.getString("color")
        val fuel=arguments?.getString("fuel")
        val lat=arguments?.getString("lat")
        val lang=arguments?.getString("lang")

        price_hr.text= "Prix par heur:"+uni_hr.toString()+"DA/HR"
        price_jr.text="Prix par jour:"+uni_jr.toString()+"DA/JR"

        val repository = RentalRepository()
        val factory = RentalViewModelFactory(repository)
        rentalViewModel = ViewModelProvider(this,factory)
                .get(RentalViewModel::class.java)

        fuel_txt.setText(fuel)
        marque.setText(matricule)
        eng_val.setText(brand)
        val_col.setText(color)
        button.setOnClickListener {view->
            val date_time=DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
                    .withZone(ZoneOffset.UTC)
                    .format(Instant.now())
            val t=LocalTime.now()
            val d=LocalDate.now()
            Toast.makeText(context,"id $id",Toast.LENGTH_LONG).show()
         /*   val rental=
                    id?.let {
                        Rental(0,26, it,date_time,LocalTime.now().toString(),d.plusDays(2).toString()+" "+t.toString(),
                            t.toString(),d.plusDays(2).toString()+" "+t.toString(),t.toString(),"jour",
                                1,1,"active")
                    }


            rental?.let { rentalViewModel.addRental(it) }
            rentalViewModel.rentalResponse
                  .observe(viewLifecycleOwner, Observer {
                response ->
                if (response.isSuccessful){
                    Log.e("Push",response.body().toString())
                    Log.e("Push",response.code().toString())

                    Toast.makeText(requireContext(),"sent sucessfully",Toast.LENGTH_SHORT).show()
                }else{
                    Log.e("Push",response.body().toString())
                    Log.e("Push",response.code().toString())
                    Toast.makeText(requireContext(),"Alert",Toast.LENGTH_SHORT).show()
                }
            })*/



         val bundle = bundleOf("id" to idcar,"img" to img,"model" to model, "hr" to uni_hr, "jr" to uni_jr,
            "brand" to brand)
            view?.findNavController()?.navigate(R.id.action_nav_listcar_to_nav_slideshow,bundle)


        }
    }

}