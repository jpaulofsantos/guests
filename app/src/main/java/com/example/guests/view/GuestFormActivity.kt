package com.example.guests.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.guests.R
import com.example.guests.constants.DataBaseConstants
import com.example.guests.databinding.ActivityGuestFormBinding
import com.example.guests.model.GuestModel
import com.example.guests.viewmodel.GuestsFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var guestsFormViewModel: GuestsFormViewModel
    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        guestsFormViewModel = ViewModelProvider(this).get(GuestsFormViewModel::class.java)

        binding.btnSave.setOnClickListener(this)
        binding.radioPresent.isChecked = true

        observe()

        loadData()

    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_save) {
            var nomeConvidado = binding.editName.text.toString()
            var radioStatus = binding.radioPresent.isChecked

            val model = GuestModel()
            model.id = guestId
            model.name = nomeConvidado
            model.presence = radioStatus

            //guestsFormViewModel.save(model)

            if (guestId != 0) {
                guestsFormViewModel.updateGuest(model)
                Toast.makeText(applicationContext, "Convidado $nomeConvidado alterado!", Toast.LENGTH_SHORT).show()
                //after conclude guest edition, go back to allGuestFragment, with updated guest list
                //startActivity(Intent(applicationContext, AllGuestsFragment::class.java))
                finish()
            } else {
                guestsFormViewModel.insert(model)
                Toast.makeText(applicationContext, "Convidado $nomeConvidado cadastrado!", Toast.LENGTH_SHORT).show()
                //after conclude guest insertion, go back to allGuestFragment, with updated guest list
                //startActivity(Intent(applicationContext, AllGuestsFragment::class.java))
                finish()
            }
        }
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            guestId = bundle.getInt(DataBaseConstants.Guest.ID)
            guestsFormViewModel.getGuest(guestId)
        }
    }

    fun observe() {
        guestsFormViewModel.guest.observe(this, Observer {
            binding.editName.setText(it.name)
            if (it.presence) {
                binding.radioPresent.isChecked = true
                binding.radioAbsent.isChecked = false
            } else {
                binding.radioAbsent.isChecked = true
                binding.radioPresent.isChecked = false
            }
            binding.btnSave.text = "Editar"
        })
    }

    fun updateGuest(guestModel: GuestModel) {
        guestsFormViewModel.updateGuest(guestModel)
    }
}