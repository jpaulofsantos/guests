package com.example.guests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.guests.databinding.ActivityGuestFormBinding

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
            guestsFormViewModel.getName(nomeConvidado)
        }
    }

    fun setObserve() {
        guestsFormViewModel.nameGuest().observe(this, Observer {
            Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()

        })
        guestsFormViewModel.isPresent().observe(this, Observer {

        })

    }
}