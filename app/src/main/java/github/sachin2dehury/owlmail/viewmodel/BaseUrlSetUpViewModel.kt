package github.sachin2dehury.owlmail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.repository.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BaseUrlSetUpViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
) : ViewModel() {

    fun saveBaseURL(baseUrl: String) = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.saveString(R.string.key_url, baseUrl)
    }

    fun resetBaseURL() = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.resetBaseURL()
    }
}