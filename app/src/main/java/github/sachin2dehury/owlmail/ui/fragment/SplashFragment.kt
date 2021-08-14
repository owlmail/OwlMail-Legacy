package github.sachin2dehury.owlmail.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.api.ResultState
import github.sachin2dehury.owlmail.viewmodel.SplashViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_url) {

    private val viewModel: SplashViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()
    }

//    private fun subscribeToObservers() {
//        viewModel.observe(viewLifecycleOwner, {
//            it?.let {
//                val navOptions = NavOptions.Builder()
//                    .setPopUpTo(R.id.splashFragment, true)
//                    .build()
//                when (it) {
//                    true -> findNavController().navigate(
//                        NavGraphDirections.actionToMailBoxFragment(getString(R.string.inbox)),
//                        navOptions
//                    )
//                    false -> findNavController().navigate(
//                        NavGraphDirections.actionToUrlFragment(),
//                        navOptions
//                    )
//                }
//            }
//        })
//    }

    private fun subscribeToObservers() = lifecycleScope.launch {
        viewModel.credentialState.collectLatest { result ->
            when (result) {
                is ResultState.Success<*> -> {
                }
                is ResultState.Error -> {
                }
                is ResultState.Loading -> {
                }
            }
        }
        viewModel.tokenState.collectLatest { result ->
            when (result) {
                is ResultState.Success<*> -> {
                }
                is ResultState.Error -> {
                }
                is ResultState.Loading -> {
                }
            }
        }
        viewModel.baseUrlState.collectLatest { result ->
            when (result) {
                is ResultState.Success<*> -> {
                }
                is ResultState.Error -> {
                }
                is ResultState.Loading -> {
                }
            }
        }
    }
}