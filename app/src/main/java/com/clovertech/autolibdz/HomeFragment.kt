package com.clovertech.autolibdz

import android.Manifest
import android.app.ActionBar
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.clovertech.autolibdz.Adapters.BorneAdapter
import com.clovertech.autolibdz.Adapters.ImageVehiculeAdapter
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.bottom_sheet_presistant_park.*
import kotlinx.android.synthetic.main.bottom_sheet_presistant_park.view.*
import kotlinx.android.synthetic.main.custom_search_dialog_black.*
import kotlinx.android.synthetic.main.custom_search_dialog_black.view.*
import kotlinx.android.synthetic.main.custom_search_dialog_park_expanded.*
import kotlinx.android.synthetic.main.custom_search_dialog_yello.*
import kotlinx.android.synthetic.main.custom_search_dialog_yello.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import com.clovertech.autolibdz.model.Borne
import com.clovertech.autolibdz.model.Vehicle
import com.clovertech.autolibdz.utils.Constants.Companion.idbornDefault
import com.clovertech.autolibdz.utils.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.util.*

class HomeFragment : Fragment() , OnMapReadyCallback , GoogleMap.OnMarkerClickListener , View.OnClickListener {

    private lateinit var googleMap: GoogleMap
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
    lateinit var searchDialogPosition : Dialog
    var searchDialogPark : Dialog? = null
    var xPosition : Float = 0.0f
    var yPosition : Float = 0.0f
    var mLastLocation: Location? = null
    internal var mFusedLocationClient: FusedLocationProviderClient? = null
    lateinit var mLocationRequest: LocationRequest

    lateinit var bornes: List<Borne>
    lateinit var adapter: BorneAdapter
 //   var idbornDefault=0
    internal var mLocationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val locationList = locationResult.locations
            if (locationList.isNotEmpty()) {
                //The last location in the list is the newest
                val location = locationList.last()
                Log.i("HomeFragment", "Location: " + location.latitude + " " + location.longitude)
                mLastLocation = location

                //Place current location marker
                val latLng = LatLng(location.latitude, location.longitude)

                //move map camera
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13.0F))

                val geo = Geocoder(requireContext(), Locale.getDefault())

                var addresses = geo.getFromLocation(latLng.latitude, latLng.longitude, 1)
                if (addresses.isNotEmpty()) {

                    ville.text = addresses[0].subAdminArea
                    region.text = addresses[0].locality
//                    Toast.makeText(requireContext(), "Address:- " + addresses[0].featureName + addresses[0].adminArea + addresses[0].locality, Toast.LENGTH_LONG).show()
                }

                if (bornes != null) {
                    val borne = closestBorne(location)
                    adapter.selectedBorne.value = borne
                    addresses = geo.getFromLocation(borne.latitude.toDouble(),
                        borne.longitude.toDouble(), 1)
                    if (addresses.isNotEmpty()) {
                        borne_name.text = borne.city
                        borne_address.text = addresses[0].locality
                    }
                }


            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchDialogPosition = Dialog(requireContext())


    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.checked_position.setOnClickListener(this)
        view.checked_park.setOnClickListener(this)
        view.search_position.setOnClickListener(this)
        view.search_park.setOnClickListener(this)
        view.search_position_dialog.setOnClickListener(this)
        view.see_cars_btn.setOnClickListener(this)

        adapter = BorneAdapter(requireContext(), this)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        mapFragment = childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        bottomSheetBehavior = from(view.bottom_sheet_layout)
        bottomSheetBehavior.state = STATE_HIDDEN
        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                val layout = mapFragment.view?.layoutParams
                if (newState == STATE_EXPANDED) {
                    activity?.bottom_bar?.visibility = View.GONE
                    images_container.visibility = View.VISIBLE
                    img_container.visibility = View.GONE
                    dots.visibility = View.VISIBLE
                }
                else if (newState == STATE_COLLAPSED) {
                    activity?.bottom_bar?.visibility = View.VISIBLE
                    images_container.visibility = View.GONE
                    img_container.visibility = View.VISIBLE
                    dots.visibility = View.GONE

                    // reduce the size of the map to ensure that the bottom bar functions correctly
                    val height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 520f , resources.displayMetrics).toInt()
                    layout?.height = height
                    mapFragment.view?.layoutParams = layout
                } else if (newState == STATE_HIDDEN) {
                    activity?.bottom_bar?.visibility = View.VISIBLE
                    images_container.visibility = View.GONE
                    img_container.visibility = View.VISIBLE
                    dots.visibility = View.GONE

                    layout?.height = ActionBar.LayoutParams.MATCH_PARENT
                    mapFragment.view?.layoutParams = layout
                }
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })

        return view
    }

    override fun onPause() {
        super.onPause()

        mFusedLocationClient?.removeLocationUpdates(mLocationCallback)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.search_position_dialog -> {
                if (search_position.visibility != View.VISIBLE){
                    bottomSheetBehavior.state = STATE_HIDDEN
                    search_position_dialog.x = xPosition
                    search_position_dialog.y = yPosition
                    search_position.visibility = View.VISIBLE
                    checked_position.visibility = View.VISIBLE
                    search_park_dialog.visibility = View.VISIBLE
                }
            }
            R.id.checked_position -> {
                moveSearchPositionDialog()
            }
          /*  R.id.park_img->{
                startActivity(Intent(context, CarsActivity::class.java))

            }*/
            R.id.checked_park -> {
                startActivity(Intent(context, CarsActivity::class.java))
               /* val borne = adapter.selectedBorne.value


              if (borne != null) {
                    park_name.text = borne.city
                  startActivity(Intent(context, CarsActivity::class.java))*/
                    // get vehicules borne
                  /*  val vehiculeAdapter = ImageVehiculeAdapter(requireContext())
                    images_container.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    images_container.adapter = vehiculeAdapter

                    RetrofitInstance.borneApi.getVehiculesBorne(borne.idBorne)
                        .enqueue(object: Callback<List<Vehicle>> {
                            override fun onResponse(
                                call: Call<List<Vehicle>>,
                                response: Response<List<Vehicle>>
                            ) {
                                if (response.isSuccessful) {
                                    vehiculeAdapter.setCarList(response.body()!!)
                                }
                            }

                            override fun onFailure(call: Call<List<Vehicle>>, t: Throwable) {
                                Log.e("list vehicules", t.localizedMessage)
                            }

                        })


                    val geo = Geocoder(requireContext(), Locale.getDefault())

                    val addresses = geo.getFromLocation(borne.latitude.toDouble(), borne.longitude.toDouble(), 1)
                    if (addresses.isNotEmpty()) {
                        location.text = addresses[0].locality

                        search_park_dialog.visibility = View.GONE
                        bottomSheetBehavior.state = STATE_COLLAPSED

                    }*/
             /*   } else {
                  //startActivity(Intent(context, CarsActivity::class.java))
                  Log.e("no borne", "no selected borne found")
                }*/


            }
            R.id.search_position -> {
                searchDialogPosition.setContentView(R.layout.custom_search_dialog_position_expanded)
                searchDialogPosition.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                searchDialogPosition.show()
            }
            R.id.search_park -> {
                if (searchDialogPark == null) {
                    searchDialogPark = Dialog(requireContext())

                    searchDialogPark!!.setContentView(R.layout.custom_search_dialog_park_expanded)
                    searchDialogPark!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                    searchDialogPark!!.bornes_list.layoutManager = LinearLayoutManager(
                        requireActivity(),
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                    searchDialogPark!!.bornes_list.adapter = adapter

                    adapter.selectedBorne.observe(viewLifecycleOwner, {
                        borne_name.text = it.city
                        searchDialogPark!!.dismiss()
                    })

                    searchDialogPark!!.searched_txt_position.addTextChangedListener {
                        if (it != null) {
                            val search = it.toString()
                            adapter.setBornes(bornes.filter {
                                it.city.contains(search)
                            })
                        }
                    }

                }

                searchDialogPark?.show()

            }
           /* R.id.see_cars_btn -> {
//                startActivity(Intent(context,FindYourCarActivity::class.java))
                startActivity(Intent(context, CarsActivity::class.java))
            }*/
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        mLocationRequest = LocationRequest()
        mLocationRequest.interval = 6000 // two minute interval
        mLocationRequest.fastestInterval = 120000
        mLocationRequest.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                //Location Permission already granted
                mFusedLocationClient?.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper()!!)
                googleMap.isMyLocationEnabled = true
            } else {
                //Request Location Permission
                checkLocationPermission()
            }
        } else {
            mFusedLocationClient?.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper()!!)
            googleMap.isMyLocationEnabled = true
        }

        //get bornes
        val call = RetrofitInstance.borneApi.getBornes()
        call.enqueue(object: Callback<List<Borne>> {
            override fun onFailure(call: Call<List<Borne>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(activity, t.localizedMessage, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Borne>>, response: Response<List<Borne>>) {
//                Toast.makeText(context, "Entered here", Toast.LENGTH_SHORT).show()
                if (response.isSuccessful) {
//                    Toast.makeText(context, "Response is successful", Toast.LENGTH_SHORT).show()

                    val borne = response.body()
                    if (borne != null) {
                        bornes = borne
                        adapter.setBornes(bornes!!)
                        bornes.forEach {
                            val borneCoordinates = LatLng(it.latitude.toString().toDouble(), it.longitude.toString().toDouble())
                            googleMap.addMarker(
                                    MarkerOptions()
                                            .position(borneCoordinates)
                                            .title("Park: ${it.city}")
                            )
                        }
                    }
                } else {
                    Toast.makeText(activity, response.code().toString(), Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        if (marker.isInfoWindowShown) {
            marker.hideInfoWindow()
        } else {
            marker.showInfoWindow()
        }
        return false
    }

    fun Float.toDips() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, resources.displayMetrics)

    private fun moveSearchPositionDialog(){
        xPosition = search_position_dialog.x
        yPosition = search_position_dialog.y
        val handler= Handler()
        handler.postDelayed({
            search_position_dialog.x = search_position_dialog.x + (search_position_dialog.width * 0.9).toFloat()
            search_position_dialog.y = (0).toFloat()
            checked_position.visibility = View.GONE
            search_position.visibility = View.GONE
        }, 500)
    }

    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                AlertDialog.Builder(requireContext())
                    .setTitle("Location Permission Needed")
                    .setMessage("This app needs the Location permission, please accept to use location functionality")
                    .setPositiveButton(
                        "OK"
                    ) { _, _ ->
                        //Prompt the user once explanation has been shown
                        ActivityCompat.requestPermissions(
                            requireActivity(),
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                            MY_PERMISSIONS_REQUEST_LOCATION
                        )
                    }
                    .create()
                    .show()


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_PERMISSIONS_REQUEST_LOCATION
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {

                        mFusedLocationClient?.requestLocationUpdates(
                            mLocationRequest,
                            mLocationCallback,
                            Looper.myLooper()!!
                        )
                        googleMap.isMyLocationEnabled = true
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(requireContext(), "permission denied", Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }

    fun closestBorne(source: Location): Borne {

        var distance = 1000000f
        var borne = bornes[0]
        idbornDefault= bornes[0].idBorne
        bornes.forEach {
            val destination = Location("destination")
            destination.latitude = it.latitude.toDouble()
            destination.longitude = it.longitude.toDouble()

            val dist = source.distanceTo(destination)
            if (dist < distance) {
                distance = dist
                borne = it
            }
        }

        return borne
    }

    companion object {
        const val MY_PERMISSIONS_REQUEST_LOCATION = 99
    }

}