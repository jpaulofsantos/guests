package com.example.guests.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guests.repository.GuestRepository

class GuestsFormViewModel: ViewModel() {

    private val repository = GuestRepository.getInstance()
    private val nameGuest = MutableLiveData<String>()
    private val isPresent = MutableLiveData<Boolean>()

    fun nameGuest(): LiveData<String> {
        return nameGuest
    }

    fun isPresent(): LiveData<Boolean> {
        return isPresent
    }

    fun getName(nome: String) {
        nameGuest.value = nome
    }

    fun getRadioStatus(status: Boolean) {
        isPresent.value = status
    }

}