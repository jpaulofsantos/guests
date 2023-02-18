package com.example.guests.constants

class DataBaseConstants private constructor(){

    object Guest {
        const val TABLE_NAME = "Guest"

        object COLUMNS {
            const val ID = "id"
            const val NAME = "name"
            const val PRESENCE = "presence"
        }
    }
}