package com.example.guests.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.guests.databinding.FragmentAllGuestsBinding
import com.example.guests.viewmodel.AllGuestsViewModel


class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null
    private val binding get() = _binding!!
    private lateinit var allGuestsViewModel: AllGuestsViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        allGuestsViewModel = ViewModelProvider(this).get(AllGuestsViewModel::class.java)

        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //fragment chamando a viewModel
        allGuestsViewModel.selectAll()

        observ()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observ() {

        allGuestsViewModel.listGuests.observe(viewLifecycleOwner) {
            val string = ""

        }
    }
}