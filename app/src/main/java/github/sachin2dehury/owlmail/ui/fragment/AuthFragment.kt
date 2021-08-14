package github.sachin2dehury.owlmail.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.api.ResultState
import github.sachin2dehury.owlmail.databinding.FragmentAuthBinding
import github.sachin2dehury.owlmail.viewmodel.AuthViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.Credentials
import java.util.*

@AndroidEntryPoint
class AuthFragment : Fragment(R.layout.fragment_auth) {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()

    private val args: AuthFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentAuthBinding.bind(view)

        viewModel.resetLoginState()
        setUpClickListener()
        subscribeToObservers()
    }

//    private fun redirectFragment() {
//        val navOptions = NavOptions.Builder()
//            .setPopUpTo(R.id.authFragment, true)
//            .build()
//        findNavController().navigate(
//            NavGraphDirections.actionToMailBoxFragment(getString(R.string.inbox)),
//            navOptions
//        )
//    }

    private fun setUpClickListener() {
        binding.buttonPrivacyPolicy.setOnClickListener {
//            findNavController().navigate(NavGraphDirections.actionToWebViewFragment(getString(R.string.privacy_policy)))
        }
        binding.buttonLogin.setOnClickListener {
//            BASE_URL = args.url
            updateCredential()
//            binding.root.hideKeyBoard()
        }
    }

    private fun updateCredential() {
        val roll = binding.editTextUserRoll.text.toString().lowercase(Locale.ROOT)
        val password = binding.editTextUserPassword.text.toString()
        viewModel.credential = Credentials.basic(roll, password)
    }

    private fun subscribeToObservers() = lifecycleScope.launch {
        viewModel.loginState.collectLatest { result ->
            when (result) {
                is ResultState.Success<*> -> {
                    //TODO save login and goto next screen
                }
                is ResultState.Error -> {
                }
                is ResultState.Loading -> {
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.buttonPrivacyPolicy.setOnClickListener(null)
        binding.buttonLogin.setOnClickListener(null)
        _binding = null
    }
}