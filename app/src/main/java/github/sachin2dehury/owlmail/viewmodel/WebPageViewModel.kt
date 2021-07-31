package github.sachin2dehury.owlmail.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.owlmail.repository.MailRepository
import javax.inject.Inject

@HiltViewModel
class WebPageViewModel @Inject constructor(
    private val mailRepository: MailRepository
) : ViewModel() {
    val token = mailRepository.getToken()
}