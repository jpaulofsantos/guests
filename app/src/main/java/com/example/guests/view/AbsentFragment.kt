package com.example.guests.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guests.constants.DataBaseConstants
import com.example.guests.databinding.FragmentAbsentBinding
import com.example.guests.view.adapter.GuestsAdapter
import com.example.guests.view.listener.OnGuestListener
import com.example.guests.viewmodel.AbsentViewModel

class AbsentFragment : Fragment() {

    private var _binding: FragmentAbsentBinding? = null
    private lateinit var absentViewModel: AbsentViewModel
    private val adapter = GuestsAdapter()
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        absentViewModel = ViewModelProvider(this).get(AbsentViewModel::class.java)
        _binding = FragmentAbsentBinding.inflate(inflater, container, false)

        binding.recyclerAbsents.layoutManager = LinearLayoutManager(context)
        binding.recyclerAbsents.adapter = adapter

        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.Guest.ID, id)
                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                absentViewModel.deleteAbsent(id)
                absentViewModel.selectAbsents()
            }
        }

        adapter.getListener(listener)

        observe()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        //fragment chamando a viewModel
        absentViewModel.selectAbsents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        absentViewModel.absentGuests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }
}
