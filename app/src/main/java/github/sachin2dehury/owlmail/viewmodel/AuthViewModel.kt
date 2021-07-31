package github.sachin2dehury.owlmail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.api.BASE_URL
import github.sachin2dehury.owlmail.api.ResultStatus
import github.sachin2dehury.owlmail.datamodel.Mail
import github.sachin2dehury.owlmail.repository.DataStoreRepository
import github.sachin2dehury.owlmail.repository.MailRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val mailRepository: MailRepository,
) : ViewModel() {

    private val _loginStatus = MutableLiveData<ResultStatus<List<Mail>>>()
    val loginStatus: LiveData<ResultStatus<List<Mail>>> = _loginStatus

    fun login(credential: String) {
        _loginStatus.postValue(ResultStatus.Loading)
        if (credential.isEmpty()) {
            _loginStatus.postValue(ResultStatus.Error(Exception("Please fill out all the fields")))
            return
        }
        mailRepository.setCredential(credential)
        CoroutineScope(viewModelScope.coroutineContext).launch {
            val result = mailRepository.login()
            _loginStatus.postValue(result)
        }
    }

    fun saveLogIn() = CoroutineScope(Dispatchers.IO).launch {
        dataStoreRepository.apply {
            saveCredential(R.string.key_credential, mailRepository.getCredential())
            saveCredential(R.string.key_token, mailRepository.getToken())
            saveCredential(R.string.key_url, BASE_URL)
            saveState(R.string.key_should_sync, true)
        }
    }

    fun resetLogin() = viewModelScope.launch {
        dataStoreRepository.resetLogin()
        mailRepository.resetLogin()
    }
}