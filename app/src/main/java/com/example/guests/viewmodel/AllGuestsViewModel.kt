package com.example.guests.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guests.model.GuestModel
import com.example.guests.repository.GuestRepository

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GuestRepository = GuestRepository.getInstance(application)

    private val listAllGuests = MutableLiveData<List<GuestModel>>()
    val listGuests: LiveData<List<GuestModel>> = listAllGuests

    private val guestDeleted = MutableLiveData<Boolean>()
    val deletedGuest: LiveData<Boolean> = guestDeleted

    //viewModel chamando o repository (vai no banco), seta a variavel listAllGuests e devolve para viewModel que está com observe em listGuests
    fun selectAll() {
        listAllGuests.value = repository.selectAll()
    }

    fun deleteGuest(id: Int) {
        guestDeleted.value = repository.deleteData(id)
    }
}