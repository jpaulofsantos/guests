package com.example.guests.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.guests.constants.DataBaseConstants

class GuestDataBase(context: Context): SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {
        private const val NAME = "guestdb"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        //criação do banco
        db.execSQL("create table " + DataBaseConstants.Guest.TABLE_NAME + " (" +
                DataBaseConstants.Guest.COLUMNS.ID + " integer primary key autoincrement, " +
                DataBaseConstants.Guest.COLUMNS.NAME + " text, " +
                DataBaseConstants.Guest.COLUMNS.PRESENCE + " integer);")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //verificação/atualização do banco

    }
}