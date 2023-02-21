package com.example.guests.view.viewholder


import android.content.DialogInterface
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

        //aplicando um evento de clique longo para deletar um convidado
        bind.nameGuest.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de Convidado")
                .setMessage("Tem certeza que deseja remover o convidado ${guest.name}?")
                .setPositiveButton("Sim") { dialog, which ->
                    listener.onDelete(guest.id)
                    Toast.makeText(itemView.context, "Convidado ${guest.name} removido!", Toast.LENGTH_SHORT).show()}
                .setNegativeButton("Não", null)
                .create()
                .show()
            true
        }
    }
}