package com.example.guests

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GuestsFormViewModel: ViewModel() {

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