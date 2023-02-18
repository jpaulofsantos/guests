package com.example.guests.repository

import android.content.ContentValues
import android.content.Context
import com.example.guests.constants.DataBaseConstants
import com.example.guests.model.GuestModel

//manipulação de dados com o BD
class GuestRepository private constructor(context: Context){

    //conexão com o banco de dados
    private val getDataBase = GuestDataBase(context)

    //Singleton - controla os acessos das instancias da classe
    // não é possível instanciar a classe
    //metodo estatico que controla a instancia
    companion object {

        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!Companion::repository.isInitialized) { //se repository não estiver inicializado, retornar a instancia
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    //insere as infos no banco
    fun insertData(guestModel: GuestModel): Boolean {

        return try {
            val db = getDataBase.writableDatabase
            val presence = if (guestModel.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.Guest.COLUMNS.NAME, guestModel.name)
            values.put(DataBaseConstants.Guest.COLUMNS.PRESENCE, presence)

            db.insert(DataBaseConstants.Guest.TABLE_NAME, null, values)
            true
        } catch (e: java.lang.Exception) {
            false
        }
    }
}