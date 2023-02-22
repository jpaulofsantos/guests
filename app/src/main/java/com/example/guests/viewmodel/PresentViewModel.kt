package com.example.guests.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guests.model.GuestModel
import com.example.guests.repository.GuestRepository

class PresentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GuestRepository = GuestRepository.getInstance(application)

    private var listPresentsGuests = MutableLiveData<List<GuestModel>>()
    var presentGuests: LiveData<List<GuestModel>> = listPresentsGuests

    fun deletePresents(id: Int) {
        repository.deleteData(id)

    }

    fun selectPresents() {
        listPresentsGuests.value = repository.selectPresent()
    }
}