package com.clovertech.autolibdz.auth.fragments

import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.*
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import com.clovertech.autolibdz.R
import kotlinx.android.synthetic.main.fragment_register2.*
import kotlinx.android.synthetic.main.fragment_register2.view.*
import kotlinx.android.synthetic.main.nav_header_main.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest

class Register2Fragment : Fragment() , View.OnClickListener{

    val REQUEST_CODE = 100
    var uploadedPicture = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register2, container, false)

        view.photo_personnelle_layout.setOnClickListener(this)
        view.selfie_permis_layout.setOnClickListener(this)
        view.photo_profile_layout.setOnClickListener(this)

        return view
    }

    override fun onClick(view : View?) {
        when(view?.id){
            R.id.photo_personnelle_layout -> {
                openGalleryForImage()
                uploadedPicture = 1
            }
            R.id.selfie_permis_layout -> {
                openCameraForImage()
                uploadedPicture = 2
            }
            R.id.photo_profile_layout -> {
                openGalleryForImage()
                uploadedPicture = 3
            }
            else -> {}
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            when(uploadedPicture){
                1 -> {

                }
                2 -> {

                }
                3 -> {

                }
            }
        }
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    private fun openCameraForImage(){
        val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePicture, REQUEST_CODE)
    }

}