package github.sachin2dehury.owlmail.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.owlmail.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class WebPageViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    val token = authRepository.authToken
}