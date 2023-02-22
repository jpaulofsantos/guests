package com.example.guests.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guests.model.GuestModel
import com.example.guests.repository.GuestRepository

class AbsentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository(application.applicationContext)

    private val listAbsentGuests = MutableLiveData<List<GuestModel>>()
    val absentGuests: LiveData<List<GuestModel>> = listAbsentGuests

    fun selectAbsents() {
        listAbsentGuests.value = repository.selectAbsent()
    }

    fun deleteAbsent(id: Int) {
        repository.deleteData(id)
    }

    fun updateGuest(guestModel: GuestModel) {
        repository.updateData(guestModel)
    }
}