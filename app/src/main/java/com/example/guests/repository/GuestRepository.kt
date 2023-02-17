package com.example.guests.repository

class GuestRepository private constructor(){

    //Singleton - controla os acessos das instancias da classe
    // não é possível instanciar a classe
    //metodo estatico que controla a instancia

    companion object {

        private lateinit var repository: GuestRepository

        fun getInstance(): GuestRepository {
            if (!Companion::repository.isInitialized) { //se repository não estiver inicializado, retornar a instancia
                repository = GuestRepository()
            }
            return repository
        }
    }
}