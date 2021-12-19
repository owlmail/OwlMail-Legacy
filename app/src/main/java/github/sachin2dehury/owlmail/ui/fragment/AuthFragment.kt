package github.sachin2dehury.owlmail.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.api.ResultState
import github.sachin2dehury.owlmail.data.Content
import github.sachin2dehury.owlmail.data.ZimbraSoap
import github.sachin2dehury.owlmail.data.auth.AuthRequest
import github.sachin2dehury.owlmail.databinding.FragmentAuthBinding
import github.sachin2dehury.owlmail.ui.utils.ResultStateListener
import github.sachin2dehury.owlmail.ui.utils.hideKeyBoard
import github.sachin2dehury.owlmail.viewmodel.AuthViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class AuthFragment : Fragment(R.layout.fragment_auth), ResultStateListener {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()

    private val args: AuthFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentAuthBinding.bind(view)

        setUpClickListener()
        subscribeToObservers()
    }

    private fun redirectFragment() {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.authFragment, true)
            .build()
//        findNavController().navigate(
//            NavGraphDirections.actionToMailBoxFragment(getString(R.string.inbox)),
//            navOptions
//        )
    }

    private fun setUpClickListener() {
        binding.privacyPolicyButton.setOnClickListener {
//            findNavController().navigate(NavGraphDirections.actionToWebViewFragment(getString(R.string.privacy_policy)))
        }
        binding.loginButton.setOnClickListener {
            updateCredential()
        }
    }

    private fun updateCredential() {
        val userName = binding.rollEditText.text.toString().lowercase(Locale.ROOT)
        val password = binding.passwordEditText.text.toString()
        viewModel.authRequest = AuthRequest(
            account = Content(userName),
            password = Content(password)
        )
        viewModel.updateLoginState(args.baseURL)
    }

    private fun subscribeToObservers() = lifecycleScope.launch {
        viewModel.loginState.collectLatest { it.mapToState() }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setEmptyState() {
    }

    override fun setErrorState(resultState: ResultState.Error) {
    }

    override fun setLoadingState() {
        binding.loginButton.isClickable = false
        binding.privacyPolicyButton.isCheckable = false
        binding.root.hideKeyBoard()
    }

    override fun setSuccessState(resultState: ResultState.Success<ZimbraSoap>) {
        resultState.value?.body?.authResponse?.let {
            it.authToken?.firstOrNull()?.content?.let { authToken ->
                viewModel.setAuthToken(authToken)
                viewModel.saveLoginCredential(authToken, it.lifetime)
            }
        }
    }
}