package com.example.guests.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guests.constants.DataBaseConstants
import com.example.guests.databinding.FragmentPresentBinding
import com.example.guests.view.adapter.GuestsAdapter
import com.example.guests.view.listener.OnGuestListener
import com.example.guests.viewmodel.PresentViewModel

class PresentFragment : Fragment() {

    private var _binding: FragmentPresentBinding? = null
    private val binding get() = _binding!!
    private lateinit var presentViewModel: PresentViewModel
    private var adapter = GuestsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        presentViewModel = ViewModelProvider(this).get(PresentViewModel::class.java)
        _binding = FragmentPresentBinding.inflate(inflater, container, false)
        
        binding.recyclerPresents.layoutManager = LinearLayoutManager(context)
        binding.recyclerPresents.adapter = adapter
        
        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                var bundle = Bundle()

                bundle.putInt(DataBaseConstants.Guest.ID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                presentViewModel.deletePresents(id)
                presentViewModel.selectPresents()
            }
        }

        adapter.getListener(listener)

        observe()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        //fragment chamando a viewModel
        presentViewModel.selectPresents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        presentViewModel.presentGuests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }
}