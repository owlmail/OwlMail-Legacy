package github.sachin2dehury.owlmail.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.api.ResultState
import github.sachin2dehury.owlmail.data.local.SessionDetails
import github.sachin2dehury.owlmail.databinding.FragmentAuthBinding
import github.sachin2dehury.owlmail.utils.ResultStateListener
import github.sachin2dehury.owlmail.utils.hideKeyBoard
import github.sachin2dehury.owlmail.utils.showKeyBoard
import github.sachin2dehury.owlmail.viewmodel.AuthViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthFragment : Fragment(R.layout.fragment_auth), ResultStateListener<SessionDetails> {

    private var _binding: FragmentAuthBinding? = null

    private val viewModel: AuthViewModel by viewModels()

    private val args: AuthFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentAuthBinding.bind(view)

        setUpClickListener()
        subscribeToObservers()
    }

    private fun setUpClickListener() = _binding?.run {
        privacyPolicyButton.setOnClickListener { }
        loginButton.setOnClickListener { makeAuthRequest() }
    }

    private fun makeAuthRequest() = _binding?.run {
        val username = rollEditText.text?.trim().toString()
        val password = passwordEditText.text?.trim().toString()
        val userDetails =
            args.sessionDetails.userDetails?.copy(username = username, password = password)
        val sessionDetails = args.sessionDetails.copy(userDetails = userDetails)
        viewModel.makeAuthRequest(sessionDetails)
    }

    private fun subscribeToObservers() = lifecycleScope.launch {
        viewModel.sessionDetails.collectLatest { it.mapToState() }
    }

    override fun setEmptyState() {
        _binding?.run {
            loginButton.isClickable = true
            privacyPolicyButton.isClickable = true
            rollEditText.showKeyBoard()
        }
    }

    override fun setErrorState(resultState: ResultState.Error<SessionDetails>) {
//        show error
    }

    override fun setLoadingState() {
        _binding?.run {
            loginButton.isClickable = false
            privacyPolicyButton.isClickable = false
            root.hideKeyBoard()
        }
    }

    override fun setSuccessState(resultState: ResultState.Success<SessionDetails>) {
        resultState.value?.let {
            viewModel.saveLoginCredential(it)
            findNavController().navigate(
                AuthFragmentDirections.actionAuthFragmentToHomeFragment(it)
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
