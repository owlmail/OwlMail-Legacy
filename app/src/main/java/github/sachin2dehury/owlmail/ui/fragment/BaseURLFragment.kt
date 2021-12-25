package github.sachin2dehury.owlmail.ui.fragment

import android.os.Bundle
import android.view.View
import android.webkit.URLUtil
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.data.SessionInfo
import github.sachin2dehury.owlmail.databinding.FragmentBaseUrlBinding
import github.sachin2dehury.owlmail.utils.hideKeyBoard

@AndroidEntryPoint
class BaseURLFragment : Fragment(R.layout.fragment_base_url) {

    private var _binding: FragmentBaseUrlBinding? = null
    private val binding get() = _binding!!

    private val args: BaseURLFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentBaseUrlBinding.bind(view)
        setupOnClickListener()
    }

    private fun setupOnClickListener() = binding.apply {
        nextButton.setOnClickListener {
            updateUrl()
        }
        privacyPolicyButton.setOnClickListener {
//            findNavController().navigate(NavGraphDirections.actionToWebViewFragment(getString(R.string.privacy_policy)))
        }
        urlEditText.doOnTextChanged { _, _, _, _ ->
            urlTextBox.error = null
        }
    }

    private fun updateUrl() = binding.apply {
        urlEditText.text?.toString()?.trim()?.let { baseURL ->
            if (URLUtil.isValidUrl(baseURL)) {
                root.hideKeyBoard()
                findNavController().navigate(
                    BaseURLFragmentDirections.actionBaseUrlSetUpFragmentToAuthFragment(
                        args.sessionInfo.copy(
                            authDetails = SessionInfo.AuthDetails(baseUrl = baseURL)
                        )
                    )
                )
            } else {
                urlTextBox.error = "Enter a valid url!"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.nextButton.setOnClickListener(null)
        binding.privacyPolicyButton.setOnClickListener(null)
        _binding = null
    }
}