package github.sachin2dehury.owlmail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.owlmail.repository.MailRepository
import javax.inject.Inject

@HiltViewModel
class SearchTabViewModel @Inject constructor(
    private val mailRepository: MailRepository
) : ViewModel() {

    fun getSearchRequestPagingSource(request: String) =
        mailRepository.getSearchRequestPagingSource("in:${request}").cachedIn(viewModelScope)
}
