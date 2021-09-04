package github.sachin2dehury.owlmail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.owlmail.paging.MailPagingSource
import github.sachin2dehury.owlmail.repository.MailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class MailBoxViewModel @Inject constructor(
    private val mailRepository: MailRepository,
) : ViewModel() {

    fun getMails(request: String) = when (mailRepository.getBox(request)) {
        null -> mailRepository.getPager(MailPagingSource(request, mailRepository))
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)
        else -> mailRepository.getPager(MailPagingSource(request, mailRepository))
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)
    }
}