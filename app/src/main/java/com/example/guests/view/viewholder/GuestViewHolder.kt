package com.example.guests.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.guests.databinding.RowGuestBinding
import com.example.guests.model.GuestModel
import com.example.guests.view.listener.OnGuestListener
//ao add a variavel listener, deve-se mapea-la no adapter
class GuestViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) : RecyclerView.ViewHolder(bind.root) {

    //ligação entre o elemento de interface e os dados
    fun bind(guest: GuestModel) {
        bind.nameGuest.text = guest.name

        //utilizado em AllGuestsFragment
        bind.nameGuest.setOnClickListener {
            listener.onClick(guest.id)
        }
    }
}