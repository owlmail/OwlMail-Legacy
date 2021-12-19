package github.sachin2dehury.owlmail.ui.utils

import github.sachin2dehury.owlmail.api.ResultState
import github.sachin2dehury.owlmail.data.ZimbraSoap

interface ResultStateListener {

    fun ResultState<ZimbraSoap>.mapToState() {
        when (this) {
            ResultState.Empty -> setEmptyState()
            is ResultState.Error -> setErrorState(this)
            ResultState.Loading -> setLoadingState()
            is ResultState.Success -> setSuccessState(this)
        }
    }

    fun setEmptyState()

    fun setErrorState(resultState: ResultState.Error)

    fun setLoadingState()

    fun setSuccessState(resultState: ResultState.Success<ZimbraSoap>)
}