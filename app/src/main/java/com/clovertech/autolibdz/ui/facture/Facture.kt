package com.clovertech.autolibdz.ui.facture
import java.io.Serializable

data class Facture (
    val id_facture : Int,
    val date_facture : String,
    val lieu_facture: String,
    val card_img : Int,
) :Serializable