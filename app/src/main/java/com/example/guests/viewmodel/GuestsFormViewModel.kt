package com.example.guests.viewmodel

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guests.model.GuestModel
import com.example.guests.repository.GuestRepository

class GuestsFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application)

    private val guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel

    fun insert(guest: GuestModel) {
        repository.insertData(guest)
    }

    fun getGuest(id: Int) {
        guestModel.value = repository.selectGuest(id)
    }

    fun updateGuest(guest: GuestModel) {
        repository.updateData(guest)
    }

    //opção de usar apenas 1 metodo e a lógica fica na viewModel
    fun save(guest: GuestModel) {
        if (guest.id == 0) {
            repository.insertData(guest)
        } else {
            repository.updateData(guest)
        }
    }

    fun delete(guestId: Int) {
        repository.deleteData(guestId)
    }

    fun selectAll() {
        repository.selectAll()
    }

    fun selectPresent() {
        repository.selectPresent()
    }

    fun selectAbsent() {
        repository.selectAbsent()
    }
}