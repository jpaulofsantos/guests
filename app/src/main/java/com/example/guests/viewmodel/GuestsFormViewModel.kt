package com.example.guests.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guests.model.GuestModel
import com.example.guests.repository.GuestRepository

class GuestsFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application)
    private val nameGuest = MutableLiveData<String>()
    private val isPresent = MutableLiveData<Boolean>()

    fun insert(guest: GuestModel) {
        repository.insertData(guest)
    }

    fun update(guest: GuestModel) {
        repository.updateData(guest)
    }
}