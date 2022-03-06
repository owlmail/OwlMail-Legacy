package github.sachin2dehury.owlmail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.api.ResultState
import github.sachin2dehury.owlmail.data.local.AuthDetails
import github.sachin2dehury.owlmail.data.local.AuthType
import github.sachin2dehury.owlmail.data.local.SessionDetails
import github.sachin2dehury.owlmail.data.local.UserDetails
import github.sachin2dehury.owlmail.repository.AuthRepository
import github.sachin2dehury.owlmail.repository.DataStoreRepository
import github.sachin2dehury.owlmail.utils.mapToAuthDetails
import github.sachin2dehury.owlmail.utils.mapToAuthType
import github.sachin2dehury.owlmail.utils.mapToResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataStoreRepository: DataStoreRepository,
) : ViewModel() {

    private val _sessionDetails = MutableStateFlow<ResultState<SessionDetails>>(ResultState.Empty)
    val sessionDetails = _sessionDetails.asStateFlow()

    fun getUserDetails() = viewModelScope.launch {
        _sessionDetails.value = ResultState.Loading
        dataStoreRepository.apply {
            val userDetails = UserDetails(
                baseUrl = readString(R.string.key_url),
                username = readString(R.string.key_username),
                password = readString(R.string.key_password),
            )
            val authDetails = AuthDetails(
                authToken = readString(R.string.key_auth_token),
                authTokenExpireTime = readLong(R.string.key_auth_token_expire_time),
            )
            val details = SessionDetails(userDetails, authDetails)
            _sessionDetails.value = when (details.mapToAuthType()) {
                AuthType.VALID_TOKEN -> ResultState.Success(details)
                AuthType.INVALID_BASEURL -> ResultState.Error(value = details)
                AuthType.INVALID_TOKEN -> ResultState.Error(value = details)
                AuthType.REFRESH_TOKEN -> refreshAuthToken(details)
            }
        }
    }

    private suspend fun refreshAuthToken(sessionDetails: SessionDetails) = when (
        val response = authRepository.makeAuthRequest(sessionDetails.userDetails).mapToResultState()
    ) {
        is ResultState.Success -> ResultState.Success(sessionDetails.copy(authDetails = response.value?.mapToAuthDetails()))
        is ResultState.Error -> ResultState.Error(response.error, sessionDetails)
        else -> ResultState.Error()
    }

    fun saveAuthDetails(authDetails: AuthDetails?) = viewModelScope.launch {
        authRepository.setAuthToken(authDetails?.authToken)
        dataStoreRepository.run {
            saveData(R.string.key_auth_token, authDetails?.authToken)
            saveData(R.string.key_auth_token_expire_time, authDetails?.authTokenExpireTime)
        }
    }
}
