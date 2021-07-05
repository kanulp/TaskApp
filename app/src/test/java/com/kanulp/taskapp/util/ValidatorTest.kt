package com.kanulp.taskapp.util


import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidatorTest{

    @Test
    fun whenInputIsNull(){
        val text = "1"
        val result = Validator.validateEmptyInput(text)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenDateInputIsValid(){
        val date = "03/04/2020"
        val result = Validator.validateDate(date)
        assertThat(result).isEqualTo(true)
    }


}