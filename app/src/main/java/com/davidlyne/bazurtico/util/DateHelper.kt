package com.davidlyne.bazurtico.util

import java.util.*

class DateHelper {
    fun getYear(): Int {
        val year = android.text.format.DateFormat.format("yyyy", Date()).toString().toInt()
        if(year > 0){
            return year
        }else{
            return 0
        }
    }

    fun getMonth(): Int {
        val year = android.text.format.DateFormat.format("MM", Date()).toString().toInt()
        if(year > 0){
            return year
        }else{
            return 0
        }
    }

    fun getDay(): Int {
        val year = android.text.format.DateFormat.format("dd", Date()).toString().toInt()
        if(year > 0){
            return year
        }else{
            return 0
        }
    }

    fun getDayName(): String {
        return android.text.format.DateFormat.format("EEEE", Date()).toString()
    }
}