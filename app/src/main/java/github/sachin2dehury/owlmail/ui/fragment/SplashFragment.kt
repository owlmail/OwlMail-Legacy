package github.sachin2dehury.owlmail.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.databinding.FragmentSplashBinding
import github.sachin2dehury.owlmail.viewmodel.SplashViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

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
        var counter = 0
        viewModel.authToken.collectLatest {
            Log.w("Sachin", "authToken : $it")
        }
        viewModel.authTokenExpireTime.collectLatest {
            if (System.currentTimeMillis() >= (it ?: 0)) {
                Log.w("Sachin", "condition : true")
            }
            Log.w("Sachin", "authTokenExpireTime : $it")
        }
        viewModel.baseUrl.collectLatest {
            Log.w("Sachin", "baseUrl : $it")
        }
        viewModel.username.collectLatest {
            Log.w("Sachin", "username : $it")
        }
        viewModel.password.collectLatest {
            Log.w("Sachin", "password : $it")
        }
        delay(2000)
        navigateToLogin()
    }

    private fun navigateToLogin() = SplashFragmentDirections.actionSplashFragmentToNavGraphAuth()
}