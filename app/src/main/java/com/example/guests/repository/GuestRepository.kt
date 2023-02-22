package com.example.guests.repository

import android.content.ContentValues
import android.content.Context
import com.example.guests.constants.DataBaseConstants
import com.example.guests.model.GuestModel

//manipulação de dados com o BD
class GuestRepository(context: Context){

    //conexão com o banco de dados
    private val getDataBase = GuestDataBase.getDataBase(context).guestDAO()

    //insere as infos no banco
    fun insertData(guestModel: GuestModel): Boolean {
        return getDataBase.insertData(guestModel) > 0
    }

    fun updateData(guestModel: GuestModel): Boolean {
        return getDataBase.updateData(guestModel) > 0
    }

    fun deleteData(guestId: Int) {
        val guest = selectGuest(guestId)
        getDataBase.deleteData(guest)
    }

    fun selectGuest(id: Int): GuestModel {
        return getDataBase.selectGuest(id)
    }

    fun selectAll(): List<GuestModel> {
        return getDataBase.selectAll()
    }

    fun selectPresent(): List<GuestModel> {
        return getDataBase.selectPresent()
    }

    fun selectAbsent(): List<GuestModel> {
        return getDataBase.selectAbsent()
    }
}