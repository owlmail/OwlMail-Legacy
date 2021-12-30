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
import github.sachin2dehury.owlmail.databinding.FragmentBaseUrlBinding
import github.sachin2dehury.owlmail.utils.hideKeyBoard
import github.sachin2dehury.owlmail.utils.showKeyBoard

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

    private fun setupOnClickListener() = binding.run {
        nextButton.setOnClickListener {
            updateSessionDetails(urlEditText.text?.toString()?.trim())
        }
        privacyPolicyButton.setOnClickListener {
//            findNavController().navigate(NavGraphDirections.actionToWebViewFragment(getString(R.string.privacy_policy)))
        }
        urlEditText.doOnTextChanged { _, _, _, _ ->
            urlTextBox.error = null
        }
    }

    private fun updateSessionDetails(baseUrl: String?) = if (URLUtil.isValidUrl(baseUrl)) {
        binding.urlTextBox.hideKeyBoard()
        val userDetails = args.sessionDetails.userDetails?.copy(baseUrl = baseUrl)
        val sessionDetails = args.sessionDetails.copy(userDetails = userDetails)
        findNavController().navigate(
            BaseURLFragmentDirections.actionBaseUrlSetUpFragmentToAuthFragment(
                sessionDetails
            )
        )
    } else {
        binding.urlTextBox.showKeyBoard()
        binding.urlTextBox.error = "Enter a valid url!"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}