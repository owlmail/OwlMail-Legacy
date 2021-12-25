package github.sachin2dehury.owlmail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.api.ResultState
import github.sachin2dehury.owlmail.data.SessionInfo
import github.sachin2dehury.owlmail.repository.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _sessionInfo = MutableStateFlow<ResultState<SessionInfo>>(ResultState.Empty)
    val sessionInfo = _sessionInfo.asStateFlow()

    private fun updateInfo() = viewModelScope.launch(Dispatchers.IO) {
        _sessionInfo.value = ResultState.Loading
        dataStoreRepository.apply {
            val sessionInfo = SessionInfo(
                SessionInfo.UserDetails(
                    readString(R.string.key_username),
                    readString(R.string.key_password),
                ),
                SessionInfo.AuthDetails(
                    readString(R.string.key_auth_token),
                    readLong(R.string.key_auth_token_expire_time),
                    readString(R.string.key_url),
                )
            )
            _sessionInfo.value = ResultState.Success(sessionInfo)
        }
    }

    init {
        updateInfo()
    }
}