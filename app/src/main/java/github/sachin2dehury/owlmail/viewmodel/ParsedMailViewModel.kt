package github.sachin2dehury.owlmail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.owlmail.paging.ParsedMailPagingSource
import github.sachin2dehury.owlmail.repository.AuthRepository
import github.sachin2dehury.owlmail.repository.MailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class ParsedMailViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val mailRepository: MailRepository
) : ViewModel() {

    fun getParsedMails(conversationId: Int) =
        mailRepository.getPager(ParsedMailPagingSource(conversationId, mailRepository))
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)

    val token = authRepository.getToken().substringAfter('=')
}