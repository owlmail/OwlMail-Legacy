package github.sachin2dehury.owlmail.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.api.ResultState
import github.sachin2dehury.owlmail.data.SessionInfo
import github.sachin2dehury.owlmail.databinding.FragmentSplashBinding
import github.sachin2dehury.owlmail.utils.ResultStateListener
import github.sachin2dehury.owlmail.viewmodel.SplashViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash), ResultStateListener<SessionInfo> {

    private val viewModel: SplashViewModel by viewModels()

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSplashBinding.bind(view)
        subscribeToObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun subscribeToObservers() = lifecycleScope.launch {
        viewModel.sessionInfo.collectLatest { it.mapToState() }
    }

    private fun navigateToNextScreen(sessionInfo: SessionInfo) = when {
        !sessionInfo.authDetails?.baseUrl.isNullOrEmpty()
                && ((!sessionInfo.userDetails?.username.isNullOrEmpty() && !sessionInfo.userDetails?.password.isNullOrEmpty()) ||
                (!sessionInfo.authDetails?.authToken.isNullOrEmpty() && ((sessionInfo.authDetails?.authTokenExpireTime
                    ?: 0) > System.currentTimeMillis())))
        -> SplashFragmentDirections.actionSplashFragmentToBaseUrlSetUpFragment(sessionInfo)
        else -> SplashFragmentDirections.actionSplashFragmentToBaseUrlSetUpFragment(sessionInfo)
    }

    override fun setEmptyState() {
        //With Image
    }

    override fun setErrorState(resultState: ResultState.Error) {
        //Not Having One
    }

    override fun setLoadingState() {
        //Animate
    }

    override fun setSuccessState(resultState: ResultState.Success<SessionInfo>) {
        resultState.value?.let { navigateToNextScreen(it) }
    }

}