package github.sachin2dehury.owlmail.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.api.ResultState
import github.sachin2dehury.owlmail.data.ZimbraSoap
import github.sachin2dehury.owlmail.databinding.FragmentHomeBinding
import github.sachin2dehury.owlmail.utils.ResultStateListener
import github.sachin2dehury.owlmail.viewmodel.SplashViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), ResultStateListener<ZimbraSoap> {

    private val viewModel: SplashViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        subscribeToObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun subscribeToObservers() = lifecycleScope.launch {
        viewModel.sessionDetails.collectLatest { }
    }

    override fun setEmptyState() {
        TODO("Not yet implemented")
    }

    override fun setErrorState(resultState: ResultState.Error<ZimbraSoap>) {
        TODO("Not yet implemented")
    }

    override fun setLoadingState() {
        TODO("Not yet implemented")
    }

    override fun setSuccessState(resultState: ResultState.Success<ZimbraSoap>) {
        TODO("Not yet implemented")
    }

}