package com.example.guests.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guests.constants.DataBaseConstants
import com.example.guests.databinding.FragmentAllGuestsBinding
import com.example.guests.model.GuestModel
import com.example.guests.view.adapter.GuestsAdapter
import com.example.guests.view.listener.OnGuestListener
import com.example.guests.viewmodel.AllGuestsViewModel


class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null
    private val binding get() = _binding!!
    private lateinit var allGuestsViewModel: AllGuestsViewModel
    private val adapter = GuestsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        allGuestsViewModel = ViewModelProvider(this).get(AllGuestsViewModel::class.java)
        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        //layout recycler
        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(context)

        //adapter recycler - responsável pela manipulação entre o layout e a lista a ser exibida
        binding.recyclerAllGuests.adapter = adapter

        //classe anônima, chamando o onclick e passando para o Adapater que recebe e enviar para o onClickLIstener do ViewHolder
        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.Guest.ID, id)
                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                allGuestsViewModel.deleteGuest(id)
                allGuestsViewModel.selectAll()
            }
        }

        adapter.getListener(listener)

        observe()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        //fragment chamando a viewModel
        allGuestsViewModel.selectAll()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        allGuestsViewModel.listGuests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }
}