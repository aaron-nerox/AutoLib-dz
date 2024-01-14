package com.clovertech.autolibdz.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clovertech.autolibdz.model.Locataire
import com.clovertech.autolibdz.model.User

class RegisterViewModel: ViewModel() {

    private val _user = MutableLiveData<User>()

    private val _locataire = MutableLiveData<Locataire>()

    val user: LiveData<User> = _user
    val locataire: LiveData<Locataire> = _locataire

    fun setUser(user: User) {
        _user.value = user
    }

    fun setLocataire(locataire: Locataire) {
        _locataire.value = locataire
    }

    fun setEmail(email: String) {
        _user.value?.userName = email
    }

    fun setNom(nom: String) {
        _user.value?.lastName = nom
    }

    fun setPrenom(prenom: String) {
        _user.value?.firstName = prenom
    }

    fun setaddress(adresse: String) {
        _user.value?.address = adresse
    }

    fun setNumTel(numtel: String) {
        _user.value?.phoneNumber = numtel
    }

    fun setPhotoPerso(photo: String) {
        _locataire.value?.profilePicture = photo
    }

    fun setSelfie(photo: String) {
        _locataire.value?.selfie = photo
    }

    fun setRefPermit(refPermit: String) {
        _locataire.value?.refPermit = refPermit
    }
    fun setAccountState(state: String) {
        _locataire.value?.accountState = state
    }


    fun setPermis(photo: String) {
        _locataire.value?.permitPicture = photo
    }


    fun setIdUser(id: Int) {
        _locataire.value?.idUser = id
    }

}