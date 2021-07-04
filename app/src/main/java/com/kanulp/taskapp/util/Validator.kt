package com.kanulp.taskapp.util

object Validator {

    fun validateInput(title:String,description:String,time:String): Boolean {
        return !(title.isEmpty() || description.isEmpty() || time.isEmpty())
    }
}