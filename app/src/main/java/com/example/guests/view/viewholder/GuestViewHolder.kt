package com.example.guests.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.guests.databinding.RowGuestBinding
import com.example.guests.model.GuestModel

class GuestViewHolder(private val bind: RowGuestBinding) : RecyclerView.ViewHolder(bind.root) {

    //ligação entre o elemento de interface e os dados
    fun bind(guest: GuestModel) {
        bind.nameGuest.text = guest.name
    }
}