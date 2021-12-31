package github.sachin2dehury.owlmail.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.api.ResultState
import github.sachin2dehury.owlmail.data.SessionDetails
import github.sachin2dehury.owlmail.databinding.FragmentSplashBinding
import github.sachin2dehury.owlmail.utils.ResultStateListener
import github.sachin2dehury.owlmail.viewmodel.SplashViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash), ResultStateListener<SessionDetails> {

    private val viewModel: SplashViewModel by viewModels()

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSplashBinding.bind(view)

        subscribeToObservers()
        viewModel.getUserDetails()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun subscribeToObservers() = lifecycleScope.launch {
        viewModel.sessionDetails.collectLatest { it.mapToState() }
    }

    override fun setEmptyState() {
        //With Image
    }

    override fun setErrorState(resultState: ResultState.Error<SessionDetails>) {
        findNavController().navigate(
            SplashFragmentDirections.actionSplashFragmentToBaseUrlSetUpFragment(
                resultState.value ?: SessionDetails()
            )
        )
    }

    override fun setLoadingState() {
        //Animate
    }

    override fun setSuccessState(resultState: ResultState.Success<SessionDetails>) {
        resultState.value?.let {
            viewModel.saveAuthDetails(it.authDetails)
            findNavController().navigate(
                SplashFragmentDirections.actionSplashFragmentToHomeFragment(
                    it
                )
            )
        }
    }

}