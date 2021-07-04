package com.kanulp.taskapp.util


import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidatorTest{

    @Test
    fun whenInputIsValid(){
        val title = "1"
        val desc = "1"
        val time = "1"
        val result = Validator.validateInput(title,desc,time)
        assertThat(result).isEqualTo(true)
    }

}