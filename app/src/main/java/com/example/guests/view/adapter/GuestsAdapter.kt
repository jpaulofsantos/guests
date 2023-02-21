package com.example.guests.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.guests.databinding.RowGuestBinding
import com.example.guests.model.GuestModel
import com.example.guests.view.listener.OnGuestListener
import com.example.guests.view.viewholder.GuestViewHolder

class GuestsAdapter: RecyclerView.Adapter<GuestViewHolder>() {

    private var guestList: List<GuestModel> = listOf()
    private lateinit var listener: OnGuestListener

    //chamado na criação do layout de cada item
    //cria e chama a fun onBindViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder { //GuestViewHolder tem a referencia para os elementos de interface/view

        //inflando o layout row_guest que foi criado para manipular cada item do recycler
        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestViewHolder(item, listener) //add listener
    }

    // atribuição dos valores no layout
    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(guestList[position])
    }

    //tamanho da lista
    override fun getItemCount(): Int {
        return guestList.count()
    }

    //método que será utilizado em AllGuestsFrament, no observer da ViewModel que recebe a lista de convidados.
    //recebe essa lista, joga o valor na variavel guestList que por sua vez é utilizada no método getItemCount
    fun updateGuests(list: List<GuestModel>) {
        guestList = list
        //chama a recycler e informa alterações
        notifyDataSetChanged()
    }

    fun getListener(listenerFrom: OnGuestListener) {
        listener = listenerFrom
    }
}