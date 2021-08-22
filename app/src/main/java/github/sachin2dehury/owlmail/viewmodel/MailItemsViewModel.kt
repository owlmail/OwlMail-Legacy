package github.sachin2dehury.owlmail.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.owlmail.repository.MailRepository
import javax.inject.Inject

@HiltViewModel
class MailItemsViewModel @Inject constructor(
    private val mailRepository: MailRepository
) : ViewModel() {

//    fun getParsedMails(conversationId: Int) =
//        mailRepository.getParsedMails(conversationId).cachedIn(viewModelScope)
//
//    fun getToken() = mailRepository.getToken()
}