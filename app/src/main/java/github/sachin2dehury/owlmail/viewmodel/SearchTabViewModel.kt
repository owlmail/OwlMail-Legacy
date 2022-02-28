package github.sachin2dehury.owlmail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.owlmail.repository.ZimbraRepository
import javax.inject.Inject

@HiltViewModel
class SearchTabViewModel @Inject constructor(
    private val zimbraRepository: ZimbraRepository,
) : ViewModel() {

    fun getSearchRequestPagingSource(request: String) =
        zimbraRepository.getSearchRequestPagingSource("in:$request").cachedIn(viewModelScope)
}
