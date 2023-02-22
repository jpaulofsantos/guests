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

    fun updateData(guestModel: GuestModel): Boolean {

        return try {
            val db = getDataBase.writableDatabase
            val presence = if (guestModel.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.Guest.COLUMNS.NAME, guestModel.name)
            values.put(DataBaseConstants.Guest.COLUMNS.PRESENCE, presence)

            //selection será interpolada com o parametro args
            val selection = DataBaseConstants.Guest.COLUMNS.ID + " = ?"
            val args = arrayOf((guestModel.id).toString())

            db.update(DataBaseConstants.Guest.TABLE_NAME, values, selection, args)
            true

        } catch (e: java.lang.Exception) {
            false
        }
    }

    fun deleteData(guestId: Int): Boolean {

        return try {
            //comunicação com o BD
            val db = getDataBase.writableDatabase

            //selection será interpolada com o parametro args
            val selection = DataBaseConstants.Guest.COLUMNS.ID + " = ?"
            val args = arrayOf((guestId).toString())

            db.delete(DataBaseConstants.Guest.TABLE_NAME, selection, args)
            true

        } catch (e: java.lang.Exception) {
            false
        }
    }

    fun selectAll(): List<GuestModel> {
        var listaGuest = mutableListOf<GuestModel>()

        try {
            val db = getDataBase.readableDatabase
            val columns = arrayOf(
                DataBaseConstants.Guest.COLUMNS.ID,
                DataBaseConstants.Guest.COLUMNS.NAME,
                DataBaseConstants.Guest.COLUMNS.PRESENCE
            )

            //método query retorna um cursor (aponta para o começo da tabela)
            val cursor = db.query(DataBaseConstants.Guest.TABLE_NAME, columns, null, null,
                null, null, null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    //pegando o id atraves do cursor, indo ate a coluna ID
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.Guest.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.Guest.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.Guest.COLUMNS.PRESENCE))

                    //presence true = 1 e false para outros valores
                    val guest = GuestModel(id, name, presence == 1)
                    //adicionando guest na lista
                    listaGuest.add(guest)
                }
            }
            cursor.close()

        } catch (e: java.lang.Exception) {
            return listaGuest
        }
        return listaGuest
    }

    fun selectPresent(): List<GuestModel> {
        var listaGuest = mutableListOf<GuestModel>()

        try {
            val db = getDataBase.readableDatabase
            val columns = arrayOf(
                DataBaseConstants.Guest.COLUMNS.ID,
                DataBaseConstants.Guest.COLUMNS.NAME,
                DataBaseConstants.Guest.COLUMNS.PRESENCE
            )

            val selection = DataBaseConstants.Guest.COLUMNS.PRESENCE + " = ?"
            val args = arrayOf("1")

            //método query retorna um cursor (aponta para o começo da tabela)
            val cursor = db.query(DataBaseConstants.Guest.TABLE_NAME, columns, selection, args,
                null, null, null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    //pegando o id atraves do cursor, indo ate a coluna ID
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.Guest.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.Guest.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.Guest.COLUMNS.PRESENCE))

                    //presence true = 1 e false para outros valores
                    val guest = GuestModel(id, name, presence == 1)
                    //adicionando guest na lista
                    listaGuest.add(guest)
                }
            }
            cursor.close()

        } catch (e: java.lang.Exception) {
            return listaGuest
        }
        return listaGuest
    }

    fun selectAbsent(): List<GuestModel> {
        var listaGuest = mutableListOf<GuestModel>()

        try {
            val db = getDataBase.readableDatabase
            val columns = arrayOf(
                DataBaseConstants.Guest.COLUMNS.ID,
                DataBaseConstants.Guest.COLUMNS.NAME,
                DataBaseConstants.Guest.COLUMNS.PRESENCE
            )
            val selection = DataBaseConstants.Guest.COLUMNS.PRESENCE + " = ?"
            val args = arrayOf("0")

            //método query retorna um cursor (aponta para o começo da tabela)
            val cursor = db.query(DataBaseConstants.Guest.TABLE_NAME, columns, selection, args,
                null, null, null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    //pegando o id atraves do cursor, indo ate a coluna ID
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.Guest.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.Guest.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.Guest.COLUMNS.PRESENCE))

                    //presence true = 1 e false para outros valores
                    val guest = GuestModel(id, name, presence == 1)
                    //adicionando guest na lista
                    listaGuest.add(guest)
                }
            }
            cursor.close()

        } catch (e: java.lang.Exception) {
            return listaGuest
        }
        return listaGuest
    }

    fun selectGuest(id: Int): GuestModel? {
        var guest: GuestModel? = null

        try {

            val db = getDataBase.readableDatabase
            val columns = arrayOf(
                DataBaseConstants.Guest.COLUMNS.ID,
                DataBaseConstants.Guest.COLUMNS.NAME,
                DataBaseConstants.Guest.COLUMNS.PRESENCE
            )
            val selection = DataBaseConstants.Guest.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            //método query retorna um cursor (aponta para o começo da tabela)
            val cursor = db.query(DataBaseConstants.Guest.TABLE_NAME, columns, selection, args,
                null, null, null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    //pegando o id atraves do cursor, indo ate a coluna ID
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.Guest.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.Guest.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.Guest.COLUMNS.PRESENCE))

                    //presence true = 1 e false para outros valores
                    guest = GuestModel(id, name, presence == 1)
                }
            }
            cursor.close()

        } catch (e: java.lang.Exception) {
            return guest
        }
        return guest
    }


}