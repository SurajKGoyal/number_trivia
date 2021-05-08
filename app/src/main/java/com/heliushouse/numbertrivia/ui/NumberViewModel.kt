package com.heliushouse.numbertrivia.ui

import com.heliushouse.numbertrivia.repository.NumberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NumberViewModel @Inject constructor(val repository: NumberRepository) {
}