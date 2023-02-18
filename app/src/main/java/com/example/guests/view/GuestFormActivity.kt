package com.example.guests.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.guests.R
import com.example.guests.databinding.ActivityGuestFormBinding
import com.example.guests.model.GuestModel
import com.example.guests.viewmodel.GuestsFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var guestsFormViewModel: GuestsFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        guestsFormViewModel = ViewModelProvider(this).get(GuestsFormViewModel::class.java)

        binding.btnSave.setOnClickListener(this)
        binding.radioPresent.isChecked = true

        setObserve()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_save) {
            var nomeConvidado = binding.editName.text.toString()
            var radioStatus = binding.radioPresent.isChecked

            val model = GuestModel(0, nomeConvidado, radioStatus)
            guestsFormViewModel.insert(model)
        }
    }

    fun setObserve() {
    }
}