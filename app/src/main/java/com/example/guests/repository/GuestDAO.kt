package com.example.guests.repository

import androidx.room.*
import com.example.guests.model.GuestModel

@Dao
interface GuestDAO {

    @Insert
    fun insertData(guest: GuestModel): Long

    @Update
    fun updateData(guest: GuestModel): Int

    @Delete
    fun deleteData(guest: GuestModel)

    @Query("SELECT * FROM Guest WHERE id = :id")
    fun selectGuest(id: Int): GuestModel

    @Query("SELECT * FROM Guest")
    fun selectAll(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 1")
    fun selectPresent(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 0")
    fun selectAbsent(): List<GuestModel>


}