package com.heliushouse.numbertrivia.ui.number

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.heliushouse.numbertrivia.MainCoroutineRule
import com.heliushouse.numbertrivia.repository.FakeRepository
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

class NumberViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: NumberViewModel

    @Before
    fun setUp() {
        viewModel = NumberViewModel(FakeRepository())
    }

    @Test
    fun getType() {
    }

    @Test
    fun getResponse() {
    }

    @Test
    fun getNumber() {
    }

    @Test
    fun getTypeString() {
    }

    @Test
    fun getReset() {
    }

    @Test
    fun getShowSnackMessage() {
    }

    @Test
    fun getDataList() {
        val arrayList = viewModel.getDataList()
        assertEquals(4, arrayList.size)
    }

    @Test
    fun submit() {
    }
}