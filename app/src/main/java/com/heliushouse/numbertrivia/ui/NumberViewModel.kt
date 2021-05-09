package com.heliushouse.numbertrivia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heliushouse.numbertrivia.model.NumberResponse
import com.heliushouse.numbertrivia.repository.NumberRepository
import com.heliushouse.numbertrivia.utils.NumberResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NumberViewModel @Inject constructor(val repository: NumberRepository): ViewModel() {
    private val _response = MutableLiveData<NumberResource>()

    val response: LiveData<NumberResource>
        get() = _response

    fun getTrivia(number: String, type: String){
        viewModelScope.launch(Dispatchers.IO) {
            _response.value = NumberResource.Loading("Trivia is on the way")
            try {
                val numberTrivia = repository.getTrivia(number, type)
                _response.value = NumberResource.Success(numberTrivia.text ?: "No Trivia Found")
            }catch (e: Exception){
                _response.value = NumberResource.Error("Something went wrong")
            }

           
        }
    }

}