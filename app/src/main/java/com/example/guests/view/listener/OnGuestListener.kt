package com.example.guests.view.listener

import com.example.guests.model.GuestModel

interface OnGuestListener {
    fun onClick(id: Int)
    fun onDelete(id: Int)
}