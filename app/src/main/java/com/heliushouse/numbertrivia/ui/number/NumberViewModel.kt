package com.heliushouse.numbertrivia.ui.number

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heliushouse.numbertrivia.repository.NumberRepository
import com.heliushouse.numbertrivia.repository.Repository
import com.heliushouse.numbertrivia.utils.Event
import com.heliushouse.numbertrivia.utils.NumberResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NumberViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _response = MutableLiveData<NumberResource>()

    val type = MutableLiveData<String>()

    val response: LiveData<NumberResource>
        get() = _response

    val number = MutableLiveData<String>()
    val typeString = MutableLiveData<String>()

    private val _reset = MutableLiveData<Event<Boolean>>()
    val reset: LiveData<Event<Boolean>>
        get() = _reset

    private val _showSnackMessage = MutableLiveData<Event<String>>()
    val showSnackMessage: LiveData<Event<String>>
        get() = _showSnackMessage

    private fun getTrivia(number: String, type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _response.postValue(NumberResource.Loading)
            try {
                val numberTrivia = repository.getTrivia(number, type)
                _response.postValue(NumberResource.Success(numberTrivia.text ?: "No Trivia Found"))
            } catch (e: Exception) {
                _response.postValue(NumberResource.Error("Something went wrong"))
            }


        }
    }

    fun getDataList(): ArrayList<String> {
        val dataList = ArrayList<String>()
        dataList.add(0, "trivia")
        dataList.add(1, "math")
        dataList.add(2, "date")
        dataList.add(3, "year")
        return dataList
    }

    fun submit(buttonType: Int) {
        if (buttonType == 1) {
            getData()
        } else {
            resetData()
        }

    }

    private fun getData() {
        if (number.value.isNullOrEmpty() || typeString.value.isNullOrEmpty()) {
            _showSnackMessage.value = Event("Something is missing!")
            return
        }
        getTrivia(number.value!!, typeString.value!!)
    }

    private fun resetData() {
        number.value = ""
        typeString.value = ""
        _reset.value = Event(true)
    }

}