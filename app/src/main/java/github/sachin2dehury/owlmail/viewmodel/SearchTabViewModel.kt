package github.sachin2dehury.owlmail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.owlmail.data.enums.ZimbraFolder
import github.sachin2dehury.owlmail.repository.ZimbraRepository
import javax.inject.Inject

@HiltViewModel
class SearchTabViewModel @Inject constructor(
    private val zimbraRepository: ZimbraRepository,
) : ViewModel() {

    var zimbraFolder: ZimbraFolder = ZimbraFolder.INBOX

    fun getSearchRequestPagingSource(request: String?) =
        zimbraRepository.getSearchRequestPagingSource(request to zimbraFolder)
            .cachedIn(viewModelScope)
}
