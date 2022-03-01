package github.sachin2dehury.owlmail.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.owlmail.data.enums.ZimbraFolder
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    var tabList = listOf(
        ZimbraFolder.INBOX,
        ZimbraFolder.SENT,
        ZimbraFolder.DRAFT,
        ZimbraFolder.JUNK,
        ZimbraFolder.TRASH
    )
}
