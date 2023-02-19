package com.example.guests.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guests.databinding.FragmentAllGuestsBinding
import com.example.guests.view.adapter.GuestsAdapter
import com.example.guests.viewmodel.AllGuestsViewModel


class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null
    private val binding get() = _binding!!
    private lateinit var allGuestsViewModel: AllGuestsViewModel
    private val adapter = GuestsAdapter()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        allGuestsViewModel = ViewModelProvider(this).get(AllGuestsViewModel::class.java)
        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        //fragment chamando a viewModel
        allGuestsViewModel.selectAll()

        //layout recycler
        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(context)

        //adapter recycler - responsável pela manipulação entre o layout e a lista a ser exibida
        binding.recyclerAllGuests.adapter = adapter

        observ()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observ() {
        allGuestsViewModel.listGuests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }
}