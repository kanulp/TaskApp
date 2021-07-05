package com.kanulp.taskapp.util

import android.util.Log
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat

object Validator {

    const val DATE_FORMAT = "MM/dd/yyyy"

    fun validateEmptyInput(text: String): Boolean {
        return text.isNotEmpty()
    }
    fun validateDate(date: String?): Boolean {
        return try {
            val df: DateFormat = SimpleDateFormat(DATE_FORMAT)
            df.isLenient = false
            df.parse(date)
            true
        } catch (e: ParseException) {
            false
        }
    }
}