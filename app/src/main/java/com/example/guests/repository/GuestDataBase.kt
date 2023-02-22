package com.example.guests.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.guests.constants.DataBaseConstants
import com.example.guests.model.GuestModel

//class GuestDataBase(context: Context): SQLiteOpenHelper(context, NAME, null, VERSION) {
@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDataBase : RoomDatabase(){

    abstract fun guestDAO(): GuestDAO

    //Singleton - controla os acessos das instancias da classe
    // não é possível instanciar a classe
    //metodo estatico que controla a instancia
    companion object {
        private lateinit var INSTANCE: GuestDataBase
        fun getDataBase(context: Context): GuestDataBase {
            if (!::INSTANCE.isInitialized) {
                synchronized(GuestDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context, GuestDataBase::class.java, "guestdb1")
                        .addMigrations(MIGRATION_1_2) //db versions
                        .allowMainThreadQueries() //threads
                        .build()
                }
            }
            return INSTANCE
        }

        private val MIGRATION_1_2: Migration = object  : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DELETE FROM Guest")
            }

        }
    }

    /*
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
    } */
}